// FAST DELIVERY
const token = "2b4b5f3e-ac78-11ee-a6e6-e60958111f48";
const serviceID = 53320;
const shopDistrictId = 1482;
const shopWardCode = 11007;

const selectCity = document.querySelector("#city");
const districtSelect = document.querySelector("#district");
const selectWardCode = document.querySelector("#ward");

const selectCityCustomer = document.querySelector("#city_customer");
const districtSelectCustomer = document.querySelector("#district_customer");
const selectWardCodeCustomer = document.querySelector("#ward_customer");

const ERROR_BORDER = '1px solid #dd3333'
const SUCCESS_BORDER = '1px solid green'
var API_BASE_URL = "/test/api/cart";
var API_BASE_COOKIE_URL = "/test/api/cart/cookie";
var LIST_ADDRESS = [];
var ADDRESS_CURRENT = {}

// FORMAT VND
function formatToVND(amount) {
    const formatter = new Intl.NumberFormat("vi-VN", {
        style: "currency",
        currency: "VND",
        minimumFractionDigits: 0, // Set to 0 to display whole numbers
    });
    return formatter.format(amount);
}

// function
getAllprovide()
function getAllprovide() {
    // const thisOrder = document.getElementById(`hoaDon${orderId}`);
    fetch( `https://dev-online-gateway.ghn.vn/shiip/public-api/master-data/province`, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            token: token,
        },
    })
        .then((res) => res.json())
        .then((data) => {
            const defaultOption = document.createElement("option");
            defaultOption.value = -1; // Set the value as needed
            defaultOption.textContent = "Chọn Tỉnh"; // Set the text content
            // Set the 'disabled' and 'selected' attributes to make it the default option
            defaultOption.disabled = true;
            defaultOption.selected = true;
            selectCity.appendChild(defaultOption);
            const options = data.data;
            for (let i = 0; i < options.length; i++) {
                const option = document.createElement("option");
                // option.value = options[i].ProvinceID; // Set the value of the option (you can change this to any value you want)
                option.text = options[i].ProvinceName; // Set the text of the option
                option.setAttribute("providecode", options[i].ProvinceID);
                selectCity.appendChild(option); // Add the option to the select element
            }
        })
        .catch((error) => console.error("Error:", error));
}

function getAllDistrict() {
    const selectedOption = selectCity.options[selectCity.selectedIndex];

    const customAttribute = selectedOption.getAttribute("providecode");
    const provinceid = parseInt(customAttribute);
    const selectDistrict = document.querySelector(` #district`);

    axios
        .get(`https://dev-online-gateway.ghn.vn/shiip/public-api/master-data/district`, {
        params: {
            province_id: provinceid,
        },
        headers: {
            Accept:  "application/json",
            token: token,
        },

    })
        .then((res) => {
            const options = res.data.data;

            for (let i = 0; i < options.length; i++) {
                const option = document.createElement("option");
                option.value = options[i].DistrictID; // Set the value of the option (you can change this to any value you want)
                option.text = options[i].DistrictName; // Set the text of the option
                option.setAttribute("districtcode", options[i].DistrictID);
                selectDistrict.appendChild(option); // Add the option to the select element
            }
        })
        .catch((error) => console.error("Error:", error));
}

function getFullWardCode() {
    const selectedOption = districtSelect.options[districtSelect.selectedIndex];
    const customAttribute = selectedOption.getAttribute("districtcode");
    const districtid = parseInt(customAttribute);
    axios.get( `https://dev-online-gateway.ghn.vn/shiip/public-api/master-data/ward`, {
        headers: {
            Accept: "application/json",
            token: token,
        },
        params: {
            district_id: districtid,
        }
    })
        .then((res) => {
            //remove all child
            const options = res.data.data;
            for (let i = 0; i < options.length; i++) {
                const option = document.createElement("option");
                option.value = options[i].WardCode; // Set the value of the option (you can change this to any value you want)
                option.text = options[i].WardName; // Set the text of the option
                option.setAttribute("WardCode", options[i].WardCode);
                selectWardCode.appendChild(option); // Add the option to the select element
            }
        })
        .catch((error) => console.error("Error:", error));
}

// GET FEE SHIPPING
const getFeeShipping = async() => {
    const district_selected = districtSelect.options[districtSelect.selectedIndex];
    const district_attribute = district_selected.getAttribute("districtcode");
    const id_district = parseInt(district_attribute);

    const ward_selected = selectWardCode.options[selectWardCode.selectedIndex];
    const ward_attribute = ward_selected.getAttribute("WardCode");
    const code_ward = parseInt(ward_attribute);

    await axios.get(
        "https://dev-online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/fee",
        {
            params: {
                from_district_id: shopDistrictId,
                from_ward_code: shopWardCode,
                service_id: serviceID,
                to_district_id: id_district,
                to_ward_code: code_ward,
                weight: 240,
            },
            headers: {
                token: token,
                Accept: "application/json",
            },
        }
    )
        .then((res) => {
            var total_amount = parseFloat(document.querySelector("#total_price_value").value)

            document.querySelector("#ship_fee").innerHTML = `
            <div class="border-bottom " style="margin-top: 10px">
                <div class=" d-flex justify-content-between">
                   <p class="font-weight-medium" style="display:inline-block;font-size: 14px;">Đơn vị vận chuyển</p>
                   <p class="font-weight-medium" style="display:inline-block;font-size: 14px;">Giao hàng nhanh</p>
                </div>
                <div class="d-flex justify-content-between">
                   <p class="font-weight-medium" style="display:inline-block;font-size: 14px;">Phí vận chuyển</p>
                   <h6 style="font-weight: bold!important;font-size: 14px;" class="font-weight-medium">${formatToVND(res.data.data.total)}</h6>
                </div>
            </div>
            `
            var total_price = total_amount + parseFloat(res.data.data.total)
            document.querySelector("#total_price").innerHTML = formatToVND(total_price)
            document.querySelector("#total_price_value").value = total_price
            document.querySelector("#ship_fee_value").value = res.data.data.total
        })
        .catch((error) => console.error("Error:", error));

    checkButtonCheckout()
}

function getNote(number){
    var noteVnPay = document.querySelector("#note_vnpay");
    var note_cash_on_delivery = document.querySelector("#note_cash_on_delivery");

    if(number === 2){
        noteVnPay.style.display = "block";
        note_cash_on_delivery.style.display = "none";
    }else{
        noteVnPay.style.display = "none";
        note_cash_on_delivery.style.display = "block";
    }
}

var open_component_voucher = false;
function openEditVoucher() {
    var voucher_zone = document.querySelector("#voucher_zone");

    if(open_component_voucher === false){
        voucher_zone.style.display = "block";
        voucher_zone.innerHTML = `
               <h5 class="section-title position-relative text-uppercase mb-3" style="border: 2px dashed #FFD333 ;">

                <div class="bg-light p-30"
                     style="
                     display: flex;
                     justify-content: space-between;"
                >
                    <input class="form-control"
                           type="text"
                           placeholder="Nhập mã giảm giá"
                           id="voucher_code"
                           style="box-shadow: inset 0 1px 2px rgba(0,0,0,.1);"
                           >
                    <button class="btn btn-block btn-primary font-weight-bold py-3"
                            style="
                                    height: calc(1.5em + 0.75rem + 9px);
                                    align-items: center;
                                    display: flex;
                                    width: 10%;
                                    transform: translate(-3px, -4px);
                                    font-size: 18px;
                            "
                            onclick="findVoucherByCode()"
                    >ÁP DỤNG</button>
                </div>
        `
        open_component_voucher = true;
        document.querySelector("#voucher_zone_note").style.display = "none"
    }else{
        voucher_zone.style.display = "none";
        open_component_voucher = false;
    }
}

const findVoucherByCode = async () => {
    var voucher_code = String(document.querySelector("#voucher_code").value);
    var coupoun_view = document.querySelector("#coupoun");
    var voucher_zone = document.querySelector("#voucher_zone");

    await axios.get(`
     /client/voucher/find-by-code?code=${voucher_code}
    `).then(res => {
        var coupoun = res.data;
        var price_discount = 0;
        var total_price = parseInt(document.querySelector("#total_amount").value);
        var ship_fee = parseInt(document.querySelector("#ship_fee_value").value);
        var old_coupoun = parseInt(document.querySelector("#coupoun_value").value);

        if(new Date(res.data.start_time).getTime() >  new Date().getTime()){
            new Notify({
                status: "error",
                title: "Voucher chưa bắt đầu",
                text: "Voucher chưa bắt đầu",
                effect: "fade",
                speed: 300,
                customClass: "",
                customIcon: "",
                showIcon: true,
                showCloseButton: false,
                autoclose: true,
                autotimeout: 3000,
                gap: 20,
                distance: 20,
                type: 1,
                position: "right top",
                customWrapper: "",
            });
            return;
        }

        if(new Date(res.data.end_time).getTime() <  new Date().getTime()){
            new Notify({
                status: "error",
                title: "Voucher đã kết thúc",
                text: "Voucher đã kết thúc",
                effect: "fade",
                speed: 300,
                customClass: "",
                customIcon: "",
                showIcon: true,
                showCloseButton: false,
                autoclose: true,
                autotimeout: 3000,
                gap: 20,
                distance: 20,
                type: 1,
                position: "right top",
                customWrapper: "",
            });
            return;
        }

        if(old_coupoun !== 0){
            new Notify({
                status: "error",
                title: "Bạn chỉ sử dụng được 1 mã giảm giá",
                text: "Bạn chỉ sử dụng được 1 mã giảm giá.",
                effect: "fade",
                speed: 300,
                customClass: "",
                customIcon: "",
                showIcon: true,
                showCloseButton: false,
                autoclose: true,
                autotimeout: 3000,
                gap: 20,
                distance: 20,
                type: 1,
                position: "right top",
                customWrapper: "",
            });
            return;
        }

        if(parseInt(res.data.min_order) > total_price){
            new Notify({
                status: "error",
                title: "Thêm mã giảm giá không thành công",
                text: "Hóa đơn của bạn không đủ điều kiện được áp dụng mã giảm giá.",
                effect: "fade",
                speed: 300,
                customClass: "",
                customIcon: "",
                showIcon: true,
                showCloseButton: false,
                autoclose: true,
                autotimeout: 3000,
                gap: 20,
                distance: 20,
                type: 1,
                position: "right top",
                customWrapper: "",
            });
        }else{
            if(coupoun.types === true){
                price_discount = parseInt(Math.round(total_price * coupoun.discount / 100));
                if( price_discount > coupoun.max_discount){
                    price_discount = coupoun.max_discount
                }
            }else{
                price_discount = coupoun.discount;
            }


            total_price = total_price - price_discount + ship_fee;

            coupoun_view.style.display = "block"
            coupoun_view.innerHTML = `
                 <div
                     style="
                            background-color: rgba(122,156,89,.2);
                            height: 44px;
                            align-items: center;
                            padding: 5px;
                            display: flex;
                            justify-content: space-between;
                    " >
                <h6 style="font-weight: bold!important;font-size: 14px;margin-top: 9px;color: #dd3333">Mã giảm giá: ${coupoun.code}</h6>
                <div>
                    <h6 style="font-weight: bold!important;font-size: 14px;margin: 0;color: #dd3333">
                      - ${formatToVND(price_discount)}
                    </h6>
                    <div style="display: block; color: #dd3333;cursor: pointer;text-align: center;font-size: 11px" onclick="deleteVoucher()">
                        (Xóa)
                    </div>
                </div>
                </div>
                
        `
            document.querySelector("#voucher_zone_note").style.display = "block"
            document.querySelector("#voucher_zone_note").innerHTML = `
            <div style="color:#7a9c59; margin-bottom: 5px"> <i class="fa fa-check"></i> Mã ưu đãi đã được áp dụng thành công. </div>
        `
            voucher_zone.style.display = "none"
            open_component_voucher = false;
            document.querySelector("#coupoun_value").value = price_discount
            document.querySelector("#total_price_value").value = total_price
            document.querySelector("#total_price").innerHTML = formatToVND(total_price)
            document.querySelector("#coupoun_object").setAttribute("coupoun", JSON.stringify(coupoun))
        }

    }).catch((error) =>{
        console.log(error)
        new Notify({
            status: "error",
            title: "Thêm mã giảm giá thất bại",
            text: error.response ? error.response.data : error.message,
            effect: "fade",
            speed: 300,
            customClass: "",
            customIcon: "",
            showIcon: true,
            showCloseButton: false,
            autoclose: true,
            autotimeout: 3000,
            gap: 20,
            distance: 20,
            type: 1,
            position: "right top",
            customWrapper: "",
        });
    }
    );
}

const deleteVoucher = () => {
    open_component_voucher = false;
    var total_amount = parseInt(document.querySelector("#total_amount").value);
    var ship_fee = parseInt(document.querySelector("#ship_fee_value").value);

    document.querySelector("#coupoun").style.display = "none";
    document.querySelector("#voucher_zone").style.display = "none"
    document.querySelector("#total_price_value").value = total_amount + ship_fee
    document.querySelector("#total_price").innerHTML = formatToVND(total_amount + ship_fee)
    document.querySelector("#voucher_zone_note").style.display = "block"
    document.querySelector("#voucher_zone_note").innerHTML = `
            <div style="color:#dd3333; margin-bottom: 5px"> <i class="fa fa-times"></i> Mã ưu đãi đã được xóa. </div>
        `
    document.querySelector("#coupoun_value").value = 0
    document.querySelector("#coupoun_object").setAttribute("coupoun", null)
}

async function checkout(){
    var city = ""
    var district = ""
    var ward = ""
    var name_house = ""
    var name = ""
    var email = ""
    var phone_number = "";

    // VALUE IF LOGIN
    if(localStorage.getItem("type_cart") === "login"){
        city = ADDRESS_CURRENT.thanh_pho;
        district = ADDRESS_CURRENT.huyen;
        ward = ADDRESS_CURRENT.xa;
        email = ADDRESS_CURRENT.email;
        name_house = ADDRESS_CURRENT.tenDiaChi;
        name = ADDRESS_CURRENT.tenNguoiNhan;
        phone_number = ADDRESS_CURRENT.sdt_nguoi_nhan;
    }else{
        city = selectCity.options[selectCity.selectedIndex].text;
        district = districtSelect.options[districtSelect.selectedIndex].text;
        ward = selectWardCode.options[selectWardCode.selectedIndex].text;
        name_house = document.querySelector("#name_house").value.trim();
        name = document.querySelector("#name").value.trim();
        email = document.querySelector("#email").value.trim();
        phone_number = document.querySelector("#phone_number").value.trim();
    }

    const note = document.querySelector("#note").value.trim();
    const ship_fee = document.querySelector("#ship_fee_value").value;
    const coupoun_value = JSON.parse(document.querySelector("#coupoun_object").getAttribute("coupoun"));
    const total_money = document.querySelector("#total_price_value").value;
    const id_bill = JSON.parse(localStorage.getItem("bill"));
    const code_bill = document.querySelector("#bill_code").value
    var payment_method = 1;
    var radios = document.getElementsByName('payment');
    const address = name_house + ", " + ward + ", " + district + ", " + city;

    for (var i = 0, length = radios.length; i < length; i++) {
        if (radios[i].checked) {
            payment_method = parseInt(radios[i].value);
            break;
        }
    }

    Swal.fire({
        title: "Xác nhận đơn hàng",
        text: "Bạn đồng ý với các thông tin và xác nhận đặt hàng",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        ncelButtonText: "Hủy bỏ",
        confirmButtonText: "Xác nhận"
    }).then(async (result) => {
        if (result.isConfirmed) {
            if(localStorage.getItem("type_cart") === "login"){
                if(String(checkButtonCheckoutCustomer()) === String(0)){
                    if(id_bill !== null){
                        var billRequest = {
                            "name" : name,
                            "phone_number": phone_number,
                            "email": email,
                            "address": address,
                            "note": note,
                            "ship_fee": ship_fee,
                            "coupoun": coupoun_value,
                            "total_money": total_money,
                            "bill": id_bill,
                            "payment_method": payment_method
                        }

                        if(String(payment_method) === "1"){
                            await axios.post(`/client/bill/add`, billRequest).then(
                                e => {
                                    console.log(JSON.parse(localStorage.getItem("cart_details")))
                                    if(localStorage.getItem("type_cart") === "login"){
                                        JSON.parse(localStorage.getItem("cart_details")).map((e) => {
                                            deleteCartDetail(e);
                                        })
                                    }else{
                                        deleteCartDetailCookie(JSON.parse(localStorage.getItem("cart_details")));
                                    }
                                    new Notify({
                                        status: "success",
                                        title: "Thành công",
                                        text: "Đơn hàng đã được tạo thành công.",
                                        effect: "fade",
                                        speed: 300,
                                        customClass: "",
                                        customIcon: "",
                                        showIcon: true,
                                        showCloseButton: false,
                                        autoclose: true,
                                        autotimeout: 3000,
                                        gap: 20,
                                        distance: 20,
                                        type: 1,
                                        position: "right top",
                                        customWrapper: "",
                                    });
                                    setTimeout(() =>{
                                        window.location.href = "/client/bill/detail/"+e.data.id
                                    }, 500)
                                }
                            ).catch(error => {
                                console.log(error)
                                new Notify({
                                    status: "error",
                                    title: "Thêm thất bại",
                                    text: "Hệ thống đang lỗi.Xin lỗi bạn vì sự bất tiện này",
                                    effect: "fade",
                                    speed: 300,
                                    customClass: "",
                                    customIcon: "",
                                    showIcon: true,
                                    showCloseButton: false,
                                    autoclose: true,
                                    autotimeout: 3000,
                                    gap: 20,
                                    distance: 20,
                                    type: 1,
                                    position: "right top",
                                    customWrapper: "",
                                });
                            }, 400)
                        }else{
                            await axios.post(`/api/vnpay/payment?total=${total_money}&orderInfor=${code_bill}&orderCode=${code_bill}`,).then(e => {
                                localStorage.setItem("bill_success", JSON.stringify(billRequest))
                                window.location.href = e.data
                            })
                        }
                    }else{
                        new Notify({
                            status: "error",
                            title: "Đã có lỗi xảy ra",
                            text: "Rất tiếc, đã có lỗi xảy ra.Vui lòng quay lại giỏ hàng để thanh toán lại.",
                            effect: "fade",
                            speed: 300,
                            customClass: "",
                            customIcon: "",
                            showIcon: true,
                            showCloseButton: false,
                            autoclose: true,
                            autotimeout: 3000,
                            gap: 20,
                            distance: 20,
                            type: 1,
                            position: "right top",
                            customWrapper: "",
                        });
                    }

                }
            }else{
                if(String(checkButtonCheckout()) === String(0)){
                    if(id_bill !== null){
                        var billRequest = {
                            "name" : name,
                            "phone_number": phone_number,
                            "email": email,
                            "address": address,
                            "note": note,
                            "ship_fee": ship_fee,
                            "coupoun": coupoun_value,
                            "total_money": total_money,
                            "bill": id_bill,
                            "payment_method": payment_method
                        }

                        if(String(payment_method) === "1"){
                            await axios.post(`/client/bill/add`, billRequest).then(
                                e => {
                                    console.log(JSON.parse(localStorage.getItem("cart_details")))
                                    if(localStorage.getItem("type_cart") === "login"){
                                        JSON.parse(localStorage.getItem("cart_details")).map((e) => {
                                            deleteCartDetail(e);
                                        })
                                    }else{
                                        deleteCartDetailCookie(JSON.parse(localStorage.getItem("cart_details")));
                                    }
                                    new Notify({
                                        status: "success",
                                        title: "Thành công",
                                        text: "Đơn hàng đã được tạo thành công.",
                                        effect: "fade",
                                        speed: 300,
                                        customClass: "",
                                        customIcon: "",
                                        showIcon: true,
                                        showCloseButton: false,
                                        autoclose: true,
                                        autotimeout: 3000,
                                        gap: 20,
                                        distance: 20,
                                        type: 1,
                                        position: "right top",
                                        customWrapper: "",
                                    });
                                    setTimeout(() =>{
                                        window.location.href = "/client/bill/detail/"+e.data.id
                                    }, 500)
                                }
                            ).catch(error => {
                                new Notify({
                                    status: "error",
                                    title: "Thêm thất bại",
                                    text: error.response ? error.response.data : error.message,
                                    effect: "fade",
                                    speed: 300,
                                    customClass: "",
                                    customIcon: "",
                                    showIcon: true,
                                    showCloseButton: false,
                                    autoclose: true,
                                    autotimeout: 3000,
                                    gap: 20,
                                    distance: 20,
                                    type: 1,
                                    position: "right top",
                                    customWrapper: "",
                                });
                            }, 400)
                        }else{
                            await axios.post(`/api/vnpay/payment?total=${total_money}&orderInfor=${code_bill}&orderCode=${code_bill}`,).then(e => {
                                localStorage.setItem("bill_success", JSON.stringify(billRequest))
                                window.location.href = e.data
                            })
                        }
                    }else{
                        new Notify({
                            status: "error",
                            title: "Đã có lỗi xảy ra",
                            text: "Rất tiếc, đã có lỗi xảy ra.Vui lòng quay lại giỏ hàng để thanh toán lại.",
                            effect: "fade",
                            speed: 300,
                            customClass: "",
                            customIcon: "",
                            showIcon: true,
                            showCloseButton: false,
                            autoclose: true,
                            autotimeout: 3000,
                            gap: 20,
                            distance: 20,
                            type: 1,
                            position: "right top",
                            customWrapper: "",
                        });
                    }

                }
            }
        }else{

        }
    });


}

function checkButtonCheckout() {
    // VALUE
    var city = selectCity.options[selectCity.selectedIndex].text;
    var district = districtSelect.options[districtSelect.selectedIndex].text;
    var ward = selectWardCode.options[selectWardCode.selectedIndex].text;
    var email = document.querySelector("#email").value;
    var name_house = document.querySelector("#name_house").value;
    var name = document.querySelector("#name").value;
    var phone_number = document.querySelector("#phone_number").value;

    // VIEW
    const city_view = selectCity;
    const district_view = districtSelect;
    const ward_view = selectWardCode;
    const email_view = document.querySelector("#email");
    const name_house_view = document.querySelector("#name_house");
    const name_view = document.querySelector("#name");
    const phone_number_view = document.querySelector("#phone_number");

    //ERROR
    const city_error = document.querySelector("#city_error");
    const district_error = document.querySelector("#district_error");
    const ward_error = document.querySelector("#ward_error");
    const email_error = document.querySelector("#email_error");
    const name_house_error = document.querySelector("#name_house_error");
    const name_error = document.querySelector("#name_error");
    const phone_error = document.querySelector("#phone_error");

    // REGEX
    var phone_regex = /(03|05|07|08|09|01[2|6|8|9])+([0-9]{8})\b/;
    var email_regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    var flag = 0;

    if(name !== "" && name !== null){
        if(name.trim() === ""){
            flag++;
            name_view.style.border = ERROR_BORDER
            name_error.style.display = 'block'
        }else{
            name_view.style.border = SUCCESS_BORDER
            name_error.style.display = 'none'
        }
    }else{
        flag++;
        name_view.style.border = ERROR_BORDER
        name_error.style.display = 'block'
    }

    if(email !== "" && email !== null){
        if(!email_regex.test(email)){
            flag++;
            email_view.style.border = ERROR_BORDER
            email_error.style.display = 'block'
        }else{
            email_view.style.border = SUCCESS_BORDER
            email_error.style.display = 'none'
        }
    }else{
        flag++;
        email_view.style.border = ERROR_BORDER
        email_error.style.display = 'block'
    }

    if(phone_number !== "" && phone_number !== null){
        if(!phone_regex.test(phone_number)){
            flag++;
            phone_number_view.style.border = ERROR_BORDER
            phone_error.style.display = 'block'
        }else{
            phone_number_view.style.border = SUCCESS_BORDER
            phone_error.style.display = 'none'
        }
    }else{
        flag++;
        phone_number_view.style.border = ERROR_BORDER
        phone_error.style.display = 'block'
    }

    if(city !== "" && city !== null && city !== undefined && city && city !== "Chọn Tỉnh" ){
        city_view.style.border = SUCCESS_BORDER
        city_error.style.display = 'none'
    }else{
        flag++;
        city_view.style.border = ERROR_BORDER
        city_error.style.display = 'block'
    }

    if(district !== "" && district !== null && district !== undefined && district !== "Chọn quận/huyện"){
        district_view.style.border = SUCCESS_BORDER
        district_error.style.display = 'none'
    }else{
        flag++;
        district_view.style.border = ERROR_BORDER
        district_error.style.display = 'block'
    }

    if(ward !== "" && ward !== null && ward !== undefined && ward !== "Chọn phường/xã"){
        ward_view.style.border = SUCCESS_BORDER
        ward_error.style.display = 'none'
    }else{
        flag++;
        ward_view.style.border = ERROR_BORDER
        ward_error.style.display = 'block'
    }

    if(name_house !== "" && name_house !== null){
        if(name_house.trim() !== ""){
            name_house_view.style.border = SUCCESS_BORDER
            name_house_error.style.display = 'none'
        }
    }else{
        flag++;
        name_house_view.style.border = ERROR_BORDER
        name_house_error.style.display = 'block'
    }

    if(flag === 0 ){
        document.querySelector("#btn_checkout").disabled = false
    }else{
        document.querySelector("#btn_checkout").disabled = true
    }

    return flag;
}

const deleteCartDetailCookie = async (cartDetailId) => {
    console.log(cartDetailId)
    try {
        const response = await axios.put(
            `${API_BASE_COOKIE_URL}/delete-carts`, cartDetailId

        );
        await get_quantity_of_cart()
    } catch (error) {
        console.log(error)
    }
};
//done

const deleteCartDetail = async (cartDetailId) => {
    try {
        const response = await axios.delete(
            `${API_BASE_URL}/delete/${cartDetailId}`
        );
        await get_quantity_of_cart()
        setTimeout(() =>{}, 400)
    } catch (error) {
        console.log(error)
    }
};

const get_quantity_of_cart = () => {
    // UPDATE QUANTITY
    var quantity = 0;
    axios.get(
        `/client/cart/quantity`
    ).then((e) =>{
        quantity = e.data
    })
    setTimeout(() => {
        document.querySelector("#quantity").innerHTML = "(" + quantity + " sản phẩm)"
    }, 100)
}

// ONLOAD
async function loadCustomer(){
    get_quantity_of_cart()
    getAllprovideCustomer()
    var customer_view = document.querySelector("#infor_customer")
    console.log(localStorage.getItem("type_cart"))
    if(localStorage.getItem("type_cart") === "login"){
        await axios.get("/client/address/find-all").then((e) => {
            console.log(e.data)
            if(e.data.length === 0){
                customer_view.innerHTML =
             `
                 <div class="row"
                    > Bạn chưa có địa chỉ
                         <button 
                            onclick="resetFormAddress()"
                            data-toggle="modal"
                            data-target="#modelId" 
                            style="
                                display: inline-block;
                                color: #004aad;
                                cursor: pointer;
                                border: none;
                                background: none;       
                             ">Thêm địa chỉ mới</button>
                    </div>
             `
            }else{
                var flag = true
                LIST_ADDRESS = e.data
               e.data.map((e) => {
                   if(String(e.trangThai) === "true"){
                       ADDRESS_CURRENT = e
                       checkButtonCheckoutCustomer(e)
                       getAllprovideByCode(e.districtCode, e.wardCode, e.provinceCode)
                       customer_view.innerHTML = `
                       <div class="row"
                      style="
                      padding: 12px 12px 12px 16px;
                      border: 1px solid #e5e5e5;
                      border-left: 6px solid #FFD333;"
                    >
                        <div style="width: 87%;color:#000">
                            <div >
                                <span style="display: inline-block;font-weight: bold">${e.tenNguoiNhan}</span> <span>${e.sdt_nguoi_nhan}</span>
                                
                                <button 
                                data-toggle="modal" 
                                data-target="#model_list_address" 
                                onclick="showListAddress()"
                                style="
                                    display: inline-block;
                                    color: #004aad;
                                    cursor: pointer;
                                    border: 0;
                                    background: none;       
                                 ">Thay đổi</button>
                            </div>
                            <div>
                                <div>
                                    ${e.tenDiaChi}
                                </div>
                                <div>
                                    ${e.xa},${e.huyen},${e.thanh_pho}
                                </div>
                            </div>          
                        </div>
                        <button onclick="changeAddress('${String(e.id)}')" style="border-color: #000;background-color: #fff;   
                                min-width: 80px;
                                height: 28px;
                                line-height: 26px;
                                font-size: 12px; 
                                font-weight: 600;
                                margin-top: 25px;    
                                position: relative;
                                z-index: 200;" data-toggle="modal" data-target="#modelId" >
                            sửa địa chỉ
                        </button>
                    </div>
                     <div class=" form-group" style="margin-top: 20px">
                        <label style="font-weight: bold!important;font-size: 14px;color: #3D464D">Ghi chú</label>
                        <textarea class="form-control"
                                  type="text"
                                  id="note"
                                  rows="4"
                                  placeholder="Nhập ghi chú của bạn"
                        >
                            </textarea>
                    </div>
                         `
                       flag = false
                       return;
                   }
               })

                if(flag){
                        customer_view.innerHTML =
                            `
                 <div class="row"
                    > Bạn chưa chọn địa chỉ nào làm địa chỉ mặc định
                         <button 
                            data-toggle="modal" 
                            data-target="#model_list_address"  
                            onclick="showListAddress()"
                            style="
                                display: inline-block;
                                color: #004aad;
                                cursor: pointer;
                                border: none;
                                background: none;       
                             ">Chọn địa chỉ</button>
                    </div>
             `
                }

                document.querySelector("#modal_checkout").innerHTML = text
            }
        }).catch((e) => {
            console.log(e)
        })
    }
}

async function showListAddress () {
    await axios.get("/client/address/find-all").then((e) => {

        if(e.data.length === 0){
            customer_view.innerHTML =
                `
                 <div class="row"
                    > Bạn chưa có địa chỉ
                         <button 
                            data-toggle="modal"
                            data-target="#modelId" 
                            style="
                                display: inline-block;
                                color: #004aad;
                                cursor: pointer;
                                border: none;
                                background: none;       
                             ">Thêm địa chỉ mới</button>
                    </div>
             `
        }else{
            var text = ""
            e.data.map((e) =>{
                text += `
                    <div class="row"
                         style="
                          padding: 12px 12px 12px 16px;
                          border: 1px solid #e5e5e5;
                          border-left: 6px solid #FFD333;
                          width: 40%;
                          font-size: 13px;
                          margin-left: 15px;
                          cursor: pointer;
                          margin-top: 20px;
                          margin-left: 50px;
                    "
                   
                    >
                        <div style="width: 87%;color:#000;" >
                            <div onclick="changeStatus('${String(e.id)}')" >
                                <span style="display: inline-block;font-weight: bold">${e.tenNguoiNhan}</span> <span>${e.sdt_nguoi_nhan}</span>
                                  ${e.trangThai === true ? " <button  style=\" display: inline-block;color: #004aad;cursor: pointer;border: 1px solid #004aad;\">Mặc định</button>" : ""}
                            </div>
                            <div onclick="changeStatus('${String(e.id)}')">
                                <div>
                                   ${e.tenDiaChi}
                                </div>
                                <div>
                                    ${e.xa},${e.huyen},${e.thanh_pho}
                                </div>
                            </div>
                        </div>
                        <button 
                            onclick="changeAddress('${String(e.id)}')"
                            style="border-color: #000;background-color: #fff;
                            min-width: 80px;
                            height: 28px;
                            line-height: 26px;
                            font-size: 12px;
                            font-weight: 600;
                            margin-top: 25px;
                            position: relative;
                            z-index: 200;" data-toggle="modal" data-target="#modelId" data-dismiss="modal"  aria-hidden="true" >
                            sửa địa chỉ
                        </button>
                         <button 
                            onclick="deleteAddress('${String(e.id)}')"
                            style="border-color: #dd3333;background-color: #fff;
                            color: #dd3333;
                            min-width: 80px;
                            height: 28px;
                            line-height: 26px;
                            font-size: 12px;
                            font-weight: 600;
                            margin-top: 25px;
                            margin-left: 15px;
                            position: relative;
                            z-index: 200;" data-toggle="modal" data-target="#modelId" data-dismiss="modal"  aria-hidden="true" >
                            Xóa
                        </button>
                    </div>
                    `
            })
            document.querySelector("#modal_checkout").innerHTML = text
        }
    }).catch((e) => {
    })
}

// function
function getAllprovideCustomer() {
    // const thisOrder = document.getElementById(`hoaDon${orderId}`);
    fetch( `https://dev-online-gateway.ghn.vn/shiip/public-api/master-data/province`, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            token: token,
        },
    })
        .then((res) => res.json())
        .then((data) => {
            const defaultOption = document.createElement("option");
            defaultOption.value = -1; // Set the value as needed
            defaultOption.textContent = "Chọn Tỉnh"; // Set the text content
            // Set the 'disabled' and 'selected' attributes to make it the default option
            defaultOption.disabled = true;
            defaultOption.selected = true;
            selectCityCustomer.appendChild(defaultOption);
            const options = data.data;
            for (let i = 0; i < options.length; i++) {
                const option = document.createElement("option");
                // option.value = options[i].ProvinceID; // Set the value of the option (you can change this to any value you want)
                option.text = options[i].ProvinceName; // Set the text of the option
                option.setAttribute("providecode", options[i].ProvinceID);
                selectCityCustomer.appendChild(option); // Add the option to the select element
            }


        })
        .catch((error) => console.error("Error:", error));
}

function getAllDistrictCustomer() {
    const selectedOption = selectCityCustomer.options[selectCityCustomer.selectedIndex];
    const customAttribute = selectedOption.getAttribute("providecode");
    const provinceid = parseInt(customAttribute);
    document.querySelector("#cityCode").value = provinceid
    const selectDistrict = document.querySelector(` #district_customer`);

    // RESET
    var option_districts = districtSelectCustomer.getElementsByTagName('option');
    for (var i=0; i<option_districts.length; i++) {
        districtSelectCustomer.removeChild(option_districts[i]);
    }

    axios
        .get(`https://dev-online-gateway.ghn.vn/shiip/public-api/master-data/district`, {
            params: {
                province_id: provinceid,
            },
            headers: {
                Accept:  "application/json",
                token: token,
            },

        })
        .then((res) => {
            const options = res.data.data;

            for (let i = 0; i < options.length; i++) {
                const option = document.createElement("option");
                option.value = options[i].DistrictID; // Set the value of the option (you can change this to any value you want)
                option.text = options[i].DistrictName; // Set the text of the option
                option.setAttribute("districtcode", options[i].DistrictID);
                selectDistrict.appendChild(option); // Add the option to the select element
            }
        })
        .catch((error) => console.error("Error:", error));
}

function getFullWardCodeCustomer() {
    const selectedOption = districtSelectCustomer.options[districtSelectCustomer.selectedIndex];
    const customAttribute = selectedOption.getAttribute("districtcode");
    const districtid = parseInt(customAttribute);
    document.querySelector("#districtCode").value = districtid

    // RESET
    var option_wards = selectWardCodeCustomer.getElementsByTagName('option');
    for (var i=0; i<option_wards.length; i++) {
        selectWardCodeCustomer.removeChild(option_wards[i]);
    }

    axios.get( `https://dev-online-gateway.ghn.vn/shiip/public-api/master-data/ward`, {
        headers: {
            Accept: "application/json",
            token: token,
        },
        params: {
            district_id: districtid,
        }
    })
        .then((res) => {
            //remove all child
            const options = res.data.data;
            for (let i = 0; i < options.length; i++) {
                const option = document.createElement("option");
                option.value = options[i].WardCode; // Set the value of the option (you can change this to any value you want)
                option.text = options[i].WardName; // Set the text of the option
                option.setAttribute("WardCode", options[i].WardCode);
                selectWardCodeCustomer.appendChild(option); // Add the option to the select element
            }
        })
        .catch((error) => console.error("Error:", error));
}

function validateAddress() {
    // VALUE
    const city = selectCityCustomer.options[selectCityCustomer.selectedIndex].text;
    const district = districtSelectCustomer.options[districtSelectCustomer.selectedIndex].text;
    const ward = selectWardCodeCustomer.options[selectWardCodeCustomer.selectedIndex].text;
    const email = document.querySelector("#email_customer").value;
    const name_house = document.querySelector("#address_customer").value;
    const name = document.querySelector("#name_customer").value;
    const phone_number = document.querySelector("#phone_number_customer").value;

    // VIEW
    const city_view = selectCityCustomer;
    const district_view = districtSelectCustomer;
    const ward_view = selectWardCodeCustomer;
    const email_view = document.querySelector("#email_customer");
    const name_house_view = document.querySelector("#address_customer");
    const name_view = document.querySelector("#name_customer");
    const phone_number_view = document.querySelector("#phone_number_customer");

    //ERROR
    const city_error = document.querySelector("#city_customer_error");
    const district_error = document.querySelector("#district_customer_error");
    const ward_error = document.querySelector("#ward_customer_error");
    const email_error = document.querySelector("#email_customer_error");
    const name_house_error = document.querySelector("#address_customer_error");
    const name_error = document.querySelector("#name_customer_error");
    const phone_error = document.querySelector("#phone_number_customer_error");

    // REGEX
    var phone_regex = /(03|05|07|08|09|01[2|6|8|9])+([0-9]{8})\b/;
    var email_regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    var flag = 0;

    if(name !== "" && name !== null){
        if(name.trim() === ""){
            flag++;
            name_view.style.border = ERROR_BORDER
            name_error.style.display = 'block'
        }else{
            name_view.style.border = SUCCESS_BORDER
            name_error.style.display = 'none'
        }
    }else{
        flag++;
        name_view.style.border = ERROR_BORDER
        name_error.style.display = 'block'
    }

    if(email !== "" && email !== null){
        if(!email_regex.test(email)){
            flag++;
            email_view.style.border = ERROR_BORDER
            email_error.style.display = 'block'
        }else{
            email_view.style.border = SUCCESS_BORDER
            email_error.style.display = 'none'
        }
    }else{
        flag++;
        email_view.style.border = ERROR_BORDER
        email_error.style.display = 'block'
    }

    if(phone_number !== "" && phone_number !== null){
        if(!phone_regex.test(phone_number)){
            flag++;
            phone_number_view.style.border = ERROR_BORDER
            phone_error.style.display = 'block'
        }else{
            phone_number_view.style.border = SUCCESS_BORDER
            phone_error.style.display = 'none'
        }
    }else{
        flag++;
        phone_number_view.style.border = ERROR_BORDER
        phone_error.style.display = 'block'
    }

    if(city !== "" && city !== null && city !== undefined && city && city !== "Chọn Tỉnh" ){
        city_view.style.border = SUCCESS_BORDER
        city_error.style.display = 'none'
    }else{
        flag++;
        city_view.style.border = ERROR_BORDER
        city_error.style.display = 'block'
    }

    if(district !== "" && district !== null && district !== undefined && district !== "Chọn quận/huyện"){
        district_view.style.border = SUCCESS_BORDER
        district_error.style.display = 'none'
    }else{
        flag++;
        district_view.style.border = ERROR_BORDER
        district_error.style.display = 'block'
    }

    if(ward !== "" && ward !== null && ward !== undefined && ward !== "Chọn phường/xã"){
        ward_view.style.border = SUCCESS_BORDER
        ward_error.style.display = 'none'
    }else{
        flag++;
        ward_view.style.border = ERROR_BORDER
        ward_error.style.display = 'block'
    }

    if(name_house !== "" && name_house !== null){
        if(name_house.trim() !== ""){
            name_house_view.style.border = SUCCESS_BORDER
            name_house_error.style.display = 'none'
        }
    }else{
        flag++;
        name_house_view.style.border = ERROR_BORDER
        name_house_error.style.display = 'block'
    }

    if(flag === 0 ){
        const ward_selected = selectWardCodeCustomer.options[selectWardCodeCustomer.selectedIndex];
        const ward_attribute = ward_selected.getAttribute("wardcode");
        const code_ward = ward_attribute;

        document.querySelector("#wardCode").value = code_ward
        document.querySelector("#btn_add_address").disabled = false
    }else{
        document.querySelector("#btn_add_address").disabled = true
    }

    return flag;
}

const addAddress = async() => {
    // VALUE
    const city = selectCityCustomer.options[selectCityCustomer.selectedIndex].text;
    const district = districtSelectCustomer.options[districtSelectCustomer.selectedIndex].text;
    const ward = selectWardCodeCustomer.options[selectWardCodeCustomer.selectedIndex].text;
    const email = document.querySelector("#email_customer").value;
    const name_house = document.querySelector("#address_customer").value;
    const name = document.querySelector("#name_customer").value;
    const phone_number = document.querySelector("#phone_number_customer").value;
    const code_ward = document.querySelector("#wardCode").value;
    const code_district = document.querySelector("#districtCode").value;
    const code_city = document.querySelector("#cityCode").value;
    const is_default = document.querySelector("#default_customer").checked
    const id = document.querySelector("#id_customer").value;
    console.log(code_ward)

    var address = {
        "id" : id,
        "name" : name,
        "email" : email,
        "phone_number" : phone_number,
        "code_city" : code_city,
        "code_district" : code_district,
        "code_ward" : code_ward,
        "city" : city,
        "ward" : ward,
        "district" : district,
        "address" : name_house,
        "is_default": is_default
    }

    Swal.fire({
        title: "Xác nhận thêm/sửa địa chỉ",
        text: "Bạn đồng ý với các thông tin trên và xác nhận thêm/sửa địa chỉ",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Thêm mới"
    }).then(async (result) => {
        if (result.isConfirmed) {
            await axios.post("/client/address/add", address).then(res => {
                console.log(res.data)
            }).catch(err => {
                console.log(err)
            })

            location.reload()
        }
    });

}

const changeStatus = async (id) => {
    console.log(id)
     await axios.put(`/client/address/change-status/${id}`).then(res => {
         location.reload()
     }).catch(err => {
         console.log(err)
     })
}

const changeAddress = async (id) =>{
    // VIEW
    const city_view = selectCityCustomer;
    const district_view = districtSelectCustomer;
    const ward_view = selectWardCodeCustomer;
    const email_view = document.querySelector("#email_customer");
    const name_house_view = document.querySelector("#address_customer");
    const name_view = document.querySelector("#name_customer");
    const phone_number_view = document.querySelector("#phone_number_customer");

    if(LIST_ADDRESS.find((e) => e.id === id) !== undefined){
        var address = LIST_ADDRESS.find((e) => e.id === id)
        console.log(address)
        email_view.value = address.email;
        name_view.value = address.tenNguoiNhan;
        phone_number_view.value = address.sdt_nguoi_nhan;
        name_house_view.value = address.tenDiaChi;
        document.querySelector("#default_customer").checked = address.trangThai
        document.querySelector("#id_customer").value = address.id
        getAllprovideByCode(address.districtCode, address.wardCode, address.provinceCode)

    }else{
        console.log("Không tìm thấy phần tử nào trong mảng")
    }
}

function getAllprovideByCode(district_code, ward_code, province_code) {
    document.querySelector("#cityCode").value = province_code
    document.querySelector("#districtCode").value = district_code
    document.querySelector("#wardCode").value = ward_code

    // const thisOrder = document.getElementById(`hoaDon${orderId}`);
    fetch( `https://dev-online-gateway.ghn.vn/shiip/public-api/master-data/province`, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            token: token,
        },
    })
        .then((res) => res.json())
        .then((data) => {
            const defaultOption = document.createElement("option");
            defaultOption.value = -1; // Set the value as needed
            defaultOption.textContent = "Chọn Tỉnh"; // Set the text content
            // Set the 'disabled' and 'selected' attributes to make it the default option
            defaultOption.disabled = true;
            selectCityCustomer.appendChild(defaultOption);
            const options = data.data;
            for (let i = 0; i < options.length; i++) {
                const option = document.createElement("option");
                // option.value = options[i].ProvinceID; // Set the value of the option (you can change this to any value you want)
                option.text = options[i].ProvinceName; // Set the text of the option
                option.setAttribute("providecode", options[i].ProvinceID);
                if(province_code === String(options[i].ProvinceID)){
                    option.selected = true;
                }
                selectCityCustomer.appendChild(option); // Add the option to the select element
            }
            getAllDistrictByCode(ward_code, district_code, province_code)
        })
        .catch((error) => console.error("Error:", error));
}

function getAllDistrictByCode(ward_code, district_code, provinceCode) {
    const selectDistrict = document.querySelector(` #district_customer`);

    axios
        .get(`https://dev-online-gateway.ghn.vn/shiip/public-api/master-data/district`, {
            params: {
                province_id: provinceCode,
            },
            headers: {
                Accept:  "application/json",
                token: token,
            },

        })
        .then((res) => {
            const options = res.data.data;

            for (let i = 0; i < options.length; i++) {
                const option = document.createElement("option");
                option.value = options[i].DistrictID; // Set the value of the option (you can change this to any value you want)
                option.text = options[i].DistrictName; // Set the text of the option
                option.setAttribute("districtcode", options[i].DistrictID);
                if(district_code === String(options[i].DistrictID)){
                    option.selected = true;
                }
                selectDistrict.appendChild(option); // Add the option to the select element
            }
            getFullWardCodeByCode(ward_code, district_code)
        })
        .catch((error) => console.error("Error:", error));
}

function getFullWardCodeByCode(ward_code, district_code) {
    console.log(ward_code+" "+ district_code)
    getFeeShippingCustomer(district_code,ward_code);
    axios.get( `https://dev-online-gateway.ghn.vn/shiip/public-api/master-data/ward`, {
        headers: {
            Accept: "application/json",
            token: token,
        },
        params: {
            district_id: district_code,
        }
    })
        .then((res) => {
            //remove all child
            const options = res.data.data;
            for (let i = 0; i < options.length; i++) {
                const option = document.createElement("option");
                option.value = options[i].WardCode; // Set the value of the option (you can change this to any value you want)
                option.text = options[i].WardName; // Set the text of the option
                option.setAttribute("WardCode", options[i].WardCode);
                if(ward_code === String(options[i].WardCode)){
                    option.selected = true;
                }
                selectWardCodeCustomer.appendChild(option); // Add the option to the select element
            }
            validateAddress()
        })
        .catch((error) => console.error("Error:", error));
}

const getFeeShippingCustomer = async(code_district, code_ward) => {

    await axios.get(
        "https://dev-online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/fee",
        {
            params: {
                from_district_id: shopDistrictId,
                from_ward_code: shopWardCode,
                service_id: serviceID,
                to_district_id: code_district,
                to_ward_code: code_ward,
                weight: 240,
            },
            headers: {
                token: token,
                Accept: "application/json",
            },
        }
    )
        .then((res) => {
            var total_amount = parseFloat(document.querySelector("#total_price_value").value)

            document.querySelector("#ship_fee").innerHTML = `
            <div class="border-bottom " style="margin-top: 10px">
                <div class=" d-flex justify-content-between">
                   <p class="font-weight-medium" style="display:inline-block;font-size: 14px;">Đơn vị vận chuyển</p>
                   <p class="font-weight-medium" style="display:inline-block;font-size: 14px;">Giao hàng nhanh</p>
                </div>
                <div class="d-flex justify-content-between">
                   <p class="font-weight-medium" style="display:inline-block;font-size: 14px;">Phí vận chuyển</p>
                   <h6 style="font-weight: bold!important;font-size: 14px;" class="font-weight-medium">${formatToVND(res.data.data.total)}</h6>
                </div>
            </div>
            `
            var total_price = total_amount + parseFloat(res.data.data.total)
            document.querySelector("#total_price").innerHTML = formatToVND(total_price)
            document.querySelector("#total_price_value").value = total_price
            document.querySelector("#ship_fee_value").value = res.data.data.total
        })
        .catch((error) => console.error("Error:", error));

}

const deleteAddress = async (id) =>{
    await axios.delete(`/client/address/delete/${id}`).then((e) =>{
        location.reload()
    }).catch((e) =>{
        console.log(e)
    })
}

function checkButtonCheckoutCustomer(e) {
    // VALUE
    const city = ADDRESS_CURRENT.thanh_pho;
    const district = ADDRESS_CURRENT.huyen;
    const ward = ADDRESS_CURRENT.xa;
    const email = ADDRESS_CURRENT.email;
    const name_house = ADDRESS_CURRENT.tenDiaChi
    const name = ADDRESS_CURRENT.tenNguoiNhan
    const phone_number = ADDRESS_CURRENT.sdt_nguoi_nhan

    // REGEX
    var phone_regex = /(03|05|07|08|09|01[2|6|8|9])+([0-9]{8})\b/;
    var email_regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    var flag = 0;

    if(name !== "" && name !== null){
        if(name.trim() === ""){
            flag++;

        }else{

        }
    }else{
        flag++;

    }

    if(email !== "" && email !== null){
        if(!email_regex.test(email)){
            flag++;
        }else{

        }
    }else{
        flag++;
    }

    if(phone_number !== "" && phone_number !== null){
        if(!phone_regex.test(phone_number)){
            flag++;
        }else{

        }
    }else{
        flag++;
    }

    if(city !== "" && city !== null && city !== undefined && city && city !== "Chọn Tỉnh" ){
    }else{
        flag++;
    }

    if(district !== "" && district !== null && district !== undefined && district !== "Chọn quận/huyện"){

    }else{
        flag++;
    }

    if(ward !== "" && ward !== null && ward !== undefined && ward !== "Chọn phường/xã"){

    }else{
        flag++;
    }

    if(name_house !== "" && name_house !== null){
        if(name_house.trim() !== ""){

        }
    }else{
        flag++;
    }

    if(flag === 0 ){
        document.querySelector("#btn_checkout").disabled = false
    }else{
        document.querySelector("#btn_checkout").disabled = true
    }

    return flag;
}

const resetFormAddress = () =>{
     document.querySelector("#email_customer").value = "";
     document.querySelector("#address_customer").value = "";
     document.querySelector("#name_customer").value = "";
     document.querySelector("#phone_number_customer").value = "";
     getAllprovideByCode(0,0,0)
     document.querySelector("#id_customer").value = ""
}




