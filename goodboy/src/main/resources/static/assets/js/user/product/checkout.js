// FAST DELIVERY
const token = "2b4b5f3e-ac78-11ee-a6e6-e60958111f48";
const serviceID = 53320;
const shopDistrictId = 1482;
const shopWardCode = 11007;
const selectCity = document.querySelector("#city");
const districtSelect = document.querySelector("#district");
const selectWardCode = document.querySelector("#ward");

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
            <div class="border-bottom ">
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

function checkout(){
    const city = selectCity.options[selectCity.selectedIndex].text;
    const district = districtSelect.options[districtSelect.selectedIndex].text;
    const ward = selectWardCode.options[selectWardCode.selectedIndex].text;
    const name_house = document.querySelector("#name_house").value;
    const name = document.querySelector("#name").value;
    const email = document.querySelector("#email").value;
    const phone_number = document.querySelector("#phone_number").value;

    const text = "Name : " + name + "\n"
                        + "Email : " + email + "\n"
                        + "Phone number : " + phone_number + "\n"
                        + "City : " + city + "\n"
                        + "District : " + district + "\n"
                        + "Ward : " + ward + "\n"
                        + "Name house : " + name_house;

    console.log(text)
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
        console.log(res.data)
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
}

function checkout(){
    const city = selectCity.options[selectCity.selectedIndex].text;
    const district = districtSelect.options[districtSelect.selectedIndex].text;
    const ward = selectWardCode.options[selectWardCode.selectedIndex].text;
    const name_house = document.querySelector("#name_house").value;
    const name = document.querySelector("#name").value;
    const email = document.querySelector("#email").value;
    const phone_number = document.querySelector("#phone_number").value;
    const note = document.querySelector("#note").value;
    const ship_fee = document.querySelector("#ship_fee_value").value;
    const coupoun_value = document.querySelector("#coupoun_value").value;
    const total_money = document.querySelector("#total_price_value").value;
    var payment_method = 1;
    var radios = document.getElementsByName('payment');

    for (var i = 0, length = radios.length; i < length; i++) {
        if (radios[i].checked) {
            payment_method = parseInt(radios[i].value);
            break;
        }
    }

    if(checkButtonCheckout() === 0){
        const text = "Name : " + name + "\n"
            + "Email : " + email + "\n"
            + "Phone number : " + phone_number + "\n"
            + "City : " + city + "\n"
            + "District : " + district + "\n"
            + "Ward : " + ward + "\n"
            + "Name house : " + name_house + "\n"
            + "note : " + note + "\n"
            + "Ship fee : " + ship_fee + "\n"
            + "Coupoun : " + coupoun_value + "\n"
            + "Total money : " + total_money + '\n'
            + "Payment method : " + payment_method;

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
