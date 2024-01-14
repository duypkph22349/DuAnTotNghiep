const hovanchooses = document.querySelectorAll(
  "input[type='radio'].hovanchoose"
);
const quantityinput = document.querySelector("input#quantitypr");
const API_BASE_URL = "/test/api/cart";

function openHoavan(cityName) {
  var i;
  var x = document.getElementsByClassName("hoavandiv");
  for (i = 0; i < x.length; i++) {
    x[i].style.display = "none";
  }
  document.getElementById(cityName).style.display = "flex";
}
async function addToCart() {
  const selectedHovan = document.querySelector(
    "input[type='radio'].hovanchoose:checked"
  );

  if (selectedHovan) {
    const divname = selectedHovan.getAttribute("id-hovan");
    const selectProduct = document.querySelector(
      `#${divname} form input[type='radio'].product-chooese:checked`
    );
    if (selectProduct) {
      const idproductselect = selectProduct.getAttribute("id-product");
      const product = await getProductDetails(idproductselect);
      try {
        await axios.get(`/client/cart/find-cart-by-id/${idproductselect}`).then(
            async (response) => {
              const cartDetail = response.data;
              try {
                const quantityValue = parseInt(quantityinput.value, 10);

                if (quantityValue) {
                  if(quantityValue > 5){
                    new Notify({
                      status: "warning",
                      title: "Đã có lỗi khi thêm sản phẩm",
                      text: `Bạn chỉ thêm được 5 sản phẩm này vào giỏ hàng`,
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

                  if(cartDetail !== '' || cartDetail !== null){
                    if(Number(cartDetail.quantity) + (quantityValue) > 5){
                      new Notify({
                        status: "warning",
                        title: "Đã có lỗi khi thêm sản phẩm",
                        text: `Bạn chỉ có thể thêm 5 sản phẩm`,
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
                  }

                  if (product.quantity < quantityValue) {
                    new Notify({
                      status: "warning",
                      title: "Số lượng không đủ",
                      text: `${product.nameProduct} - ${product.idPattern.name} - ${product.idSize.name} còn - ${product.quantity} sản phẩm`,
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
                  else {
                    await axios
                        .get(
                            `${API_BASE_URL}/add/${idproductselect}?quantity=${quantityValue}`
                        )
                        .then((response) => {
                          console.log("Item added to cart:", response.data);
                          new Notify({
                            status: "success",
                            title: "Thêm vào giỏ hàng thành công",
                            text: "Đã thêm sản phẩm",
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
                        })
                        .catch((error) => {
                          new Notify({
                            status: "error",
                            title: "Thêm thất bại",
                            text:  error.response ? error.response.data : error.message,
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
                        });

                    await  get_quantity_of_cart();
                  }
                }
              } catch (err) {
                new Notify({
                  status: "warning",
                  title: "xẩy ra lỗi không đúng",
                  text: `Số lượng không đúng"`,
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
        ).catch( async (error) => {
          try {
            const quantityValue = parseInt(quantityinput.value, 10);

            if (quantityValue) {
              if (product.quantity < quantityValue) {
                new Notify({
                  status: "warning",
                  title: "Số lượng không đủ",
                  text: `${product.nameProduct} - ${product.idPattern.name} - ${product.idSize.name} còn - ${product.quantity} sản phẩm`,
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
              else {
                await axios
                    .get(
                        `${API_BASE_URL}/add/${idproductselect}?quantity=${quantityValue}`
                    )
                    .then((response) => {
                      console.log("Item added to cart:", response.data);
                      new Notify({
                        status: "success",
                        title: "Thêm vào giỏ hàng thành công",
                        text: "Đã thêm sản phẩm",
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
                    })
                    .catch((error) => {
                      new Notify({
                        status: "error",
                        title: "Thêm thất bại",
                        text:  error.response ? error.response.data : error.message,
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
                    });

                await  get_quantity_of_cart();
              }
            }
          } catch (err) {
            new Notify({
              status: "warning",
              title: "xẩy ra lỗi không đúng",
              text: `Số lượng không đúng"`,
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
        })
      } catch (error) {
        alert("Không thể tìm thấy sản phẩm !!!");
        throw error;
      }

    } else {
      new Notify({
        status: "warning",
        title: "Chọn sản phẩm",
        text: "Vui lòng kích thước",
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
  } else {
    new Notify({
      status: "warning",
      title: "Chọn sản phẩm",
      text: "Vui lòng hoa văn",
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
async function getProductDetails(id) {
  try {
    const response = await fetch(`/product/test/rest/detail/${id}`);
    const data = response.json();
    return data;
  } catch (error) {
    alert("Không thể tìm thấy sản phẩm !!!");
    throw error;
  }
}

async function getCartDetail(id) {

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
