// FAST DELIVERY
const token = "2b4b5f3e-ac78-11ee-a6e6-e60958111f48";
const serviceID = 53320;
const shopDistrictId = 1482;
const shopWardCode = 11007;
const selectCity = document.querySelector("#city");
const districtSelect = document.querySelector("#district");
const selectWardCode = document.querySelector("#ward");
const ERROR_BORDER = '1px solid #dd3333'
const SUCCESS_BORDER = '1px solid green'
var API_BASE_URL = "/test/api/cart";
var API_BASE_COOKIE_URL = "/test/api/cart/cookie";

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
    const city = selectCity.options[selectCity.selectedIndex].text;
    const district = districtSelect.options[districtSelect.selectedIndex].text;
    const ward = selectWardCode.options[selectWardCode.selectedIndex].text;
    const name_house = document.querySelector("#name_house").value.trim();
    const name = document.querySelector("#name").value.trim();
    const email = document.querySelector("#email").value.trim();
    const phone_number = document.querySelector("#phone_number").value.trim();
    const note = document.querySelector("#note").value.trim();
    const ship_fee = document.querySelector("#ship_fee_value").value;
    const coupoun_value = JSON.parse(document.querySelector("#coupoun_object").getAttribute("coupoun"));
    const total_money = document.querySelector("#total_price_value").value;
    const id_bill = JSON.parse(localStorage.getItem("bill"));
    var payment_method = 1;
    var radios = document.getElementsByName('payment');
    const address = name_house + ", " + ward + ", " + district + ", " + city;

    for (var i = 0, length = radios.length; i < length; i++) {
        if (radios[i].checked) {
            payment_method = parseInt(radios[i].value);
            break;
        }
    }

    console.log(id_bill)

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
                        window.location.href = "/index"
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

function checkButtonCheckout() {
    // VALUE
    const city = selectCity.options[selectCity.selectedIndex].text;
    const district = districtSelect.options[districtSelect.selectedIndex].text;
    const ward = selectWardCode.options[selectWardCode.selectedIndex].text;
    const email = document.querySelector("#email").value;
    const name_house = document.querySelector("#name_house").value;
    const name = document.querySelector("#name").value;
    const phone_number = document.querySelector("#phone_number").value;

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
function loadCustomer(){
    get_quantity_of_cart()
    var customer_view = document.querySelector("#infor_customer")

    if(localStorage.getItem("type_cart") === "login"){
        customer_view.innerHTML = `
         <div class="row"
                  style="
                  padding: 12px 12px 12px 16px;
                  border: 1px solid #e5e5e5;
                  border-left: 6px solid #FFD333;"
                >
                    <div style="width: 87%;color:#000">
                        <div >
                            <span style="display: inline-block;font-weight: bold">Tám Hoàng</span> <span>0326235071</span>
                            
                            <span style="display: inline-block;color: #004aad;cursor: pointer;">Thay đổi</span>
                        </div>
                        <div>
                            <div>
                                Đường Trần Thánh Tông
                            </div>
                            <div>
                                Xã Minh Tân, Huyện Kiến Xương, Tỉnh Thái Bình
                            </div>
                        </div>          
                    </div>
                    <button style="border-color: #000;background-color: #fff;   
                            min-width: 80px;
                            height: 28px;
                            line-height: 26px;
                            font-size: 12px; 
                            font-weight: 600;
                            margin-top: 25px;">
                        sửa địa chỉ
                    </button>
                </div>
        `
    }
}