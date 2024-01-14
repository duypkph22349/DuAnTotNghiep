var API_BASE_URL = "/test/api/cart";
var API_BASE_COOKIE_URL = "/test/api/cart/cookie";


const loadPaymentSuccess = async () =>{
    var code_bill = document.querySelector("#code_bill")
    var payment_bill = document.querySelector("#payment_bill")

    var bill = JSON.parse(localStorage.getItem("bill_success"));
    await axios.post(`/client/bill/add`, bill).then(
        e => {
            console.log(JSON.parse(localStorage.getItem("cart_details")))
            if(localStorage.getItem("type_cart") === "login"){
                JSON.parse(localStorage.getItem("cart_details")).map((e) => {
                    deleteCartDetail(e);
                })
            }else{
                deleteCartDetailCookie(JSON.parse(localStorage.getItem("cart_details")));
            }
            get_quantity_of_cart();
        }
    ).catch(error => {
        console.log(error)
    })

    code_bill.innerHTML = `
     Bạn đã thanh toán thành công
    `
    payment_bill.innerHTML = `
     Số tiền thanh toán là : <span style="color: #dd3333">${formatToVND(bill.total_money)}</span>
    `
}

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

function formatToVND(amount) {
    const formatter = new Intl.NumberFormat("vi-VN", {
        style: "currency",
        currency: "VND",
        minimumFractionDigits: 0, // Set to 0 to display whole numbers
    });
    return formatter.format(amount);
}