window.onload = function () {
  var inputs = document.getElementsByTagName('input[type="number"]');
  for (var i = 0; i < inputs.length; i++) {
    if (inputs[i].type.toLowerCase() === "number") {
      inputs[i].addEventListener("change", function (e) {
        var num = parseFloat(e.target.value);
        e.target.value = num.toFixed(0);
      });
    }
  }
};

function updateProductDetail(event) {
  var errorCount = 0;
  event.preventDefault();
  const form = event.target;
  const id = form.querySelector("input[name='id']");
  const quantity = form.querySelector("input[name='quantity']");
  const price = form.querySelector("input[name='price']");
  const description = form.querySelector("textarea[name='discription']");
  // const image = form.querySelector("input[name='image']");
  const buttonsubmit = form.querySelector(".savebtn");
  resetErrorElement(quantity);
  if (quantity.value < 0) {
    setErrorElement(quantity, "Số lượng phải lớn hơn 0");
    errorCount++;
  }
  resetErrorElement(price);
  if (price.value < 0) {
    setErrorElement(price, "Giá phải lớn hơn 0");
    errorCount++;
  }
  resetErrorElement(buttonsubmit);
  if (errorCount > 0) {
    setErrorElement(buttonsubmit, "Kiểm tra lại đầu vào");
  } else {
    const data = {
      id: id.value,
      quantity: quantity.value,
      price: price.value,
      description: description.value, // Corrected field name
    };
    axios
      .post("/admin/managerproduct/update/product", data)
      .then(function (response) {
        console.log(response.data);
        new Notify({
          status: "success",
          title: "Thành công",
          text: "Cập nhật thành công",
          effect: "fade",
          speed: 300,
          customClass: "",
          customIcon: "",
          showIcon: true,
          showCloseButton: true,
          autoclose: true,
          autotimeout: 3000,
          gap: 20,
          distance: 20,
          type: 1,
          position: "right top",
          customWrapper: "",
        });
      })
      .catch(function (error) {
        console.error("Error update product:", error);
        new Notify({
          status: "error",
          title: "Thất bại",
          text: "Cập nhật thất bại",
          effect: "fade",
          speed: 300,
          customClass: "",
          customIcon: "",
          showIcon: true,
          showCloseButton: true,
          autoclose: true,
          autotimeout: 3000,
          gap: 20,
          distance: 20,
          type: 1,
          position: "right top",
          customWrapper: "",
        });
      });
  }
  const imageInput = form.querySelector("input[name='imageproduct']");
  const divimage = form.querySelector("div.image-div");
  var imagecount = divimage.querySelectorAll("img");
  const imageFiles = Array.from(imageInput.files);
  const updateImage = (imageFile) => {
    const formData = new FormData();
    formData.append("image", imageFile);

    return axios.post(
      `/admin/managerproduct/update/product/${id.value}/saveimage`,
      formData,
      {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      }
    );
  };

  if (imagecount.length > 0) {
    // Update each image using Axios and display the returned image URL
    imageFiles.forEach((imageFile) => {
      updateImage(imageFile)
        .then((response) => {
          console.log("Image updated successfully:", response.data);
          updateDivImage(response.data);
        })
        .catch((error) => {
          console.error("Error updating image:", error);
        });
    });
  } else {
    divimage.innerHTML = ""; // Clear existing images
    imageFiles.forEach((imageFile) => {
      updateImage(imageFile)
        .then((response) => {
          console.log("Image updated successfully:", response.data);
          updateDivImage(response.data);
        })
        .catch((error) => {
          // Handle error, if needed
          console.error("Error updating image:", error);
        });
    });
  }
  const updateDivImage = (imageUrl) => {
    // Create a new <a> element
    const newAnchor = document.createElement("a");
    newAnchor.href = imageUrl; // Set the href attribute to the image URL
    newAnchor.target = "_blank"; // Open the link in a new tab/window
    newAnchor.classList.add("border", "border-2", "p-2", "d-inline-block");

    // Create a new <img> element
    const newImage = document.createElement("img");
    newImage.src = imageUrl;
    newImage.alt = "Ảnh sản phẩm";
    newImage.classList.add("img-fluid");
    newImage.style.height = "30px";

    // Append the <img> element as a child to the <a> element
    newAnchor.appendChild(newImage);
    divimage.appendChild(newAnchor);
  };
}
function addProductDetail(event) {
  var errorCount = 0;
  event.preventDefault();
  const form = event.target;
  const idProduct = form.querySelector("input[name='idProduct']");
  const idSize = form.querySelector("select[name='idSize']");
  const idPattern = form.querySelector("select[name='idPattern']");
  const quantity = form.querySelector("input[name='quantity']");
  const price = form.querySelector("input[name='price']");
  const description = form.querySelector("textarea[name='description']");
  const status = form.querySelector("input[name='status']:checked").value; // Get the selected radio button value
  const imageInput = form.querySelector("input[name='listimage']");

  const data = {
    idProduct: idProduct.value,
    idSize: idSize.value,
    idPattern: idPattern.value,
    quantity: quantity.value,
    price: price.value,
    description: description.value,
    status: status,
  };
  axios
    .post("/admin/managerproduct/productdetail/save", data)
    .then(function (response) {
      closeModalAddProduct();
      new Notify({
        status: "success",
        title: "Thành công",
        text: "Thêm thành công",
        effect: "fade",
        speed: 300,
        customClass: "",
        customIcon: "",
        showIcon: true,
        showCloseButton: true,
        autoclose: true,
        autotimeout: 3000,
        gap: 20,
        distance: 20,
        type: 1,
        position: "right top",
        customWrapper: "",
      });
      console.log(response);
      if (response.data.id) {
        const imageFiles = Array.from(imageInput.files);
        const updateImage = (imageFile) => {
          const formData = new FormData();
          formData.append("image", imageFile);
          return axios.post(
            `/admin/managerproduct/update/product/${response.data.id}/saveimage`,
            formData,
            {
              headers: {
                "Content-Type": "multipart/form-data",
              },
            }
          );
        };
        imageFiles.forEach((imageFile) => {
          updateImage(imageFile)
            .then((response) => {
              console.log("Image updated successfully:", response.data);
              new Notify({
                status: "success",
                title: "Thành công",
                text: "Thêm ảnh thành công",
                effect: "fade",
                speed: 300,
                customClass: "",
                customIcon: `<img src="${response.data}" alt="Ảnh sản phẩm" class="img-fluid" style="height: 30px;">`,
                showIcon: true,
                showCloseButton: true,
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
              console.error("Error updating image:", error);
            });
        });
      }
    })
    .catch(function (error) {
      if (error.response.data.errors) {
        error.response.data.errors.forEach((error) => {
          new Notify({
            status: "error",
            title: "Kiểm tra lại đầu vào",
            text: error,
            effect: "fade",
            speed: 300,
            customClass: "",
            customIcon: "",
            showIcon: true,
            showCloseButton: true,
            autoclose: true,
            autotimeout: 3000,
            gap: 20,
            distance: 20,
            type: 1,
            position: "right top",
            customWrapper: "",
          });
        });
      } else if (error.response.data.message) {
        new Notify({
          status: "error",
          title: "Kiểm tra lại đầu vào",
          text: error.response.data.message,
          effect: "fade",
          speed: 300,
          customClass: "",
          customIcon: "",
          showIcon: true,
          showCloseButton: true,
          autoclose: true,
          autotimeout: 3000,
          gap: 20,
          distance: 20,
          type: 1,
          position: "right top",
          customWrapper: "",
        });
      }
    });
}

function openModalAddProduct(idproduct) {
  const modalAddProduct = document.getElementById("modelAddProductDetail");
  const form = modalAddProduct.querySelector("form");
  form.reset();
  const input = modalAddProduct.querySelector("form input[name='idProduct']");
  input.value = idproduct;
  $("#modelAddProductDetail").modal("show");
  $(`#modelEditProduct`).modal("hide");
}
function closeModalAddProduct() {
  const modalAddProduct = document.getElementById("modelAddProductDetail");
  const input = modalAddProduct.querySelector("form input[name='idProduct']");
  idproduct = input.value;
  $("#modelAddProductDetail").modal("hide");
  generateEditForm(idproduct);
}
async function fetchData(endpoint) {
  try {
    const response = await axios.get(`/admin/managerproduct/${endpoint}`);
    return response.data;
  } catch (error) {
    console.error(`Error getting ${endpoint}:`, error);
    throw error;
  }
}
async function getCategory() {
  return fetchData("category");
}

async function getOrigin() {
  return fetchData("origin");
}

async function getBrand() {
  return fetchData("brand");
}

async function getMaterial() {
  return fetchData("material");
}

async function getStyles() {
  return fetchData("styles");
}

async function getHoaVan() {
  return fetchData("pattern");
}

async function getKichThuoc() {
  return fetchData("size");
}

async function getSanPham(id) {
  return fetchData(`product/get/${id}`);
}

const modalEditProduct = document.getElementById("modelEditProduct");
async function generateEditForm(idproduct) {
  const modalContent = modalEditProduct.querySelector("div.modal-content");
  modalContent.innerHTML = "";
  const product = await getSanPham(idproduct);
  console.log(product);
  modalContent.appendChild(generateModalHeader(product));
  modalContent.appendChild(await generateModalBody(product));
  modalContent.appendChild(generateModalFooter());
  $("#modelEditProduct").modal("show");
}
async function generateFormProduct(product) {
  console.log("generate Form Product");
  const form = document.createElement("form");
  form.setAttribute("enctype", "multipart/form-data");
  form.setAttribute("class", "row form container-fluid");
  form.setAttribute("onsubmit", "updateProduct(event)");
  const materialCbb = await getMaterial();
  const stylesCbb = await getStyles();
  const brandCbb = await getBrand();
  const categoryCbb = await getCategory();
  const originCbb = await getOrigin();
  form.innerHTML = `
    <input type="hidden" name="id" id="id" value="${product.id}">
    <div class="text-center col-1 col-md-4 col-sm-12">
        <div>
            <div class="row">
            ${
              product.imageProducts.length === 0
                ? "<span>Không có ảnh nào</span>"
                : ""
            }
            ${product.imageProducts
              .map(
                (image) => `
                  <div class="position-relative col-3">
                  <img class="p-1 image-fluid w-100" src="${image.img}" alt="ảnh sản phẩm">
                  <a href="javascript:void(0);" onclick="removeImageProduct(this)" class="position-absolute top-0 start-100 translate-middle p-1 bg-danger border border-light rounded-circle text-white">
         <i class="fas fa-times fa-xs" aria-hidden="true"></i>
          </a>
                  </div>
                `
              )
              .join("")}

            </div>
        </div>
        <div class="form-group">
            <input class="form-control" type="file" id="formFile" name="listimage" multiple>
        </div>
    </div>
    <div class="row  col-11 col-md-8 col-sm-12">
        <div class="form-group">
            <label>Name:</label>
            <input type="text" name="name" class="form-control" value="${
              product.name
            }">
        </div>
        <div class="form-group col-sm-12 col-md-6 col-lg-4 col-xl-3 mb-3 ">
            <label for="idOrigin" class="m-auto fw-bold">Nguồn Gốc:</label>
            <select class="form-control form-group-0 text-center" id="idOrigin" name="idOrigin">
                <option selected disabled value ="-1"> Chọn Nguồn Gốc</option>
                ${originCbb
                  .map(
                    (option) => `
                    <option value="${option.id}" ${
                      product.idOrigin?.id == option.id ? "selected" : ""
                    }>
                        ${option.name}
                    </option>
                `
                  )
                  .join("")}
            </select>
        </div>
        <div class="form-group col-sm-12 col-md-6 col-lg-4 col-xl-3 mb-3">
            <label for="idBrand" class="m-auto fw-bold">Thương hiệu:</label>
            <select class="form-control form-group-0 text-center" id="idBrand" name="idBrand">
                <option selected disabled  value ="-1"> Chọn Thương hiệu</option>
                ${brandCbb
                  .map(
                    (option) => `
                    <option value="${option.id}" ${
                      product.idBrand?.id == option.id ? "selected" : ""
                    }>
                        ${option.name}
                    </option>
                `
                  )
                  .join("")}
            </select>
        </div>
        <div class="form-group col-sm-12 col-md-6 col-lg-4 col-xl-3 mb-3">
            <label for="idMaterial" class="m-auto fw-bold">Chất Liệu</label>
            <select class="form-control form-group-0 text-center" id="idMaterial" name="idMaterial">
                <option selected disabled value ="-1"> Chọn Chất Liệu</option>
                ${materialCbb
                  .map(
                    (option) => `
                    <option value="${option.id}" ${
                      product.idMaterial?.id == option.id ? "selected" : ""
                    }>
                        ${option.name}
                    </option>
                `
                  )
                  .join("")}
            </select>
        </div>
        <div class="form-group col-sm-12 col-md-6 col-lg-4 col-xl-3 mb-3">
            <label for="idCategory" class="m-auto fw-bold">Loại hàng</label>
            <select class="form-control form-group-0 text-center" id="idCategory" name="idCategory">
                <option selected disabled value ="-1"> Chọn Loại hàng</option>
                ${categoryCbb
                  .map(
                    (option) => `
                    <option value="${option.id}" ${
                      product.idCategory?.id == option.id ? "selected" : ""
                    }>
                        ${option.name}
                    </option>
                `
                  )
                  .join("")}
            </select>
        </div>
        <div class="form-group col-sm-12 col-md-6 col-lg-4 col-xl-3 mb-3">
            <label for="idStyles" class="m-auto fw-bold">Phong cách</label>
            <select class="form-control form-group-0 text-center" id="idStyles" name="idStyles">
                <option selected disabled  value ="-1"> Chọn Phong cách</option>
                ${stylesCbb
                  .map(
                    (option) => `
                    <option value="${option.id}" ${
                      product.idStyles?.id == option.id ? "selected" : ""
                    }>
                        ${option.name}
                    </option>
                `
                  )
                  .join("")}
            </select>
        </div>
        <div class="form-group">
            <label>Trạng Thái: </label>
            <input type="radio" name="status" id="r1" value="1" ${
              product.status == 1 ? "checked" : ""
            }>
            <label for="r1">Hoạt động</label>
            <input type="radio" name="status" id="r2" value="0" ${
              product.status == 0 ? "checked" : ""
            }>
            <label for="r2">Không hoạt động</label>
        </div>
        <input type="submit" class="btn btn-success" value="Update">
    </div>
`;
  return form;
}
function generateModalHeader(product) {
  const div = document.createElement("div");
  div.classList.add("modal-header");
  const innerHtml = `
    <h5 class="modal-title" id="modelEditProductLabel">${product.name}</h5>
    <a href="/admin/product/dicription/update/${product.id}" class="btn btn-info mx-5 text-light">Chỉnh sửa mô tả</a>
    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
`;
  div.innerHTML = innerHtml;
  return div;
}
async function generateModalBody(product) {
  const div = document.createElement("div");
  div.classList.add("modal-body");

  try {
    const form = await generateFormProduct(product);
    console.log(form);
    div.innerHTML = `
      <div class="row">
        ${form.outerHTML}
        ${generateProductDetails(product).outerHTML}
      </div>
    `;
  } catch (error) {
    console.error("Error generating modal body:", error);
    // Handle the error as needed
  }

  return div;
}
function generateFormProductDetails(product) {
  const form = document.createElement("form");
  form.setAttribute("onsubmit", "updateProductDetail(event)");
  form.innerHTML = `
<input type="hidden" name="id" value="${product.id}">
<div class="row">
    <div class="col-4 row mb-3">
        <div class="mb-3">
            <input class="form-control" type="file" multiple id="formFile" name="imageproduct">
        </div>
    </div>
    <div class="col-8 mb-3 image-div d-flex d-flex justify-content-around">
    ${product.imageProducts.length === 0 ? "<span>Không có ảnh nào</span>" : ""}
        ${product.imageProducts
          .map(
            (image) => `
            <div>
            <a href="${image.img}" target="_blank" class="border border-2 p-2 d-inline-block">
                <img src="${image.img}" alt="Ảnh sản phẩm" class="img-fluid" style="height: 30px;">
            </a>
            </div>
            `
          )
          .join("")}
            </div>
    <div class="col-6 row mb-3">
        <label for="inputNumber" class="col-sm-6 col-form-label">Số lượng</label>
        <div class="col-sm-6">
            <input type="number" class="form-control" name="quantity" value="${
              product.quantity
            }">
        </div>
    </div>
    <div class="col-6 row mb-3">
        <label for="inputNumber" class="col-sm-4 col-form-label">Giá</label>
        <div class="col-sm-8">
            <input type="number" class="form-control" name="price" value="${
              product.price
            }">
        </div>
    </div>
    <div class="form-floating mb-3 col-9">
        <textarea class="form-control" placeholder="Leave a comment here" id="floatingTextarea" style="height: 18px;" name="discription">${
          product.description
        }</textarea>
        <label for="floatingTextarea">Mô tả</label>
    </div>
    <div class="col-3">
        <button type="submit" class="savebtn btn btn-success">Lưu</button>
    </div>
</div>
`;
  return form;
}
function generateProductDetails(product) {
  const div = document.createElement("div");
  div.classList.add("col-12");
  div.innerHTML = `
    <hr>
    <button type="button" onclick="openModalAddProduct(${
      product.id
    })" class="btn btn-success">Thêm sản phẩm chi tiết</button>
    <div>
        ${
          product.productdetails && product.productdetails.length === 0
            ? "Không có chi tiết sản phẩm"
            : ""
        }
        ${
          product.productdetails
            ? product.productdetails
                .map(
                  (listPettern) => `
                <div>
                    <div class="fw-bolder">${
                      listPettern[0].idPattern?.name
                    }</div>
                    ${listPettern
                      .map(
                        (pddt) => `
                        <div>
                            <span class="fw-bold">Mã: ${pddt.code}</span>
                            <span class="fw-bold">- Kích thước: ${
                              pddt.idSize?.name
                            }</span>
                        </div>
                        ${generateFormProductDetails(pddt).outerHTML}
                    `
                      )
                      .join("")}
                  </div>
            `
                )
                .join("")
            : ""
        }
    </div>
`;
  return div;
}
function generateModalFooter() {
  const div = document.createElement("div");
  div.classList.add("modal-footer");
  div.innerHTML = `
    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
`;
  return div;
}

function updateProduct(event) {
  event.preventDefault();
  const form = event.target;
  const imageInput = form.querySelector("input[name='listimage']");
  const formData = {
    id: form.querySelector('input[name="id"]').value,
    name: form.querySelector('input[name="name"]').value,
    idOrigin: form.querySelector('select[name="idOrigin"]').value,
    idBrand: form.querySelector('select[name="idBrand"]').value,
    idMaterial: form.querySelector('select[name="idMaterial"]').value,
    idCategory: form.querySelector('select[name="idCategory"]').value,
    idStyles: form.querySelector('select[name="idStyles"]').value,
    status: form.querySelector('input[name="status"]:checked').value,
  };
  // Log all information in FormData
  console.log(formData);
  // Make an Axios POST request
  axios
    .post("/admin/managerproduct/product/save", formData)
    .then((response) => {
      // Handle the successful response here
      console.log(response.data);
      new Notify({
        status: "success",
        title: "Thành công",
        text: `Cập nhật "${response.data.name}" thành công`,
        effect: "fade",
        speed: 300,
        customClass: "",
        customIcon: "",
        showIcon: true,
        showCloseButton: true,
        autoclose: true,
        autotimeout: 3000,
        gap: 20,
        distance: 20,
        type: 1,
        position: "right top",
        customWrapper: "",
      });
      if (response.data.id) {
        const imageFiles = Array.from(imageInput.files);
        const updateImage = (imageFile) => {
          const formData = new FormData();
          formData.append("image", imageFile);
          return axios.post(
            `/admin/managerproduct/product/${response.data.id}/image/save`,
            formData,
            {
              headers: {
                "Content-Type": "multipart/form-data",
              },
            }
          );
        };
        imageFiles.forEach((imageFile) => {
          updateImage(imageFile)
            .then((response) => {
              console.log("Image updated successfully:", response.data);
              new Notify({
                status: "success",
                title: "Thành công",
                text: "Thêm ảnh thành công",
                effect: "fade",
                speed: 300,
                customClass: "",
                customIcon: `<img src="${response.data.img}" alt="Ảnh sản phẩm" class="img-fluid" style="height: 30px;">`,
                showIcon: true,
                showCloseButton: true,
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
              console.error("Error updating image:", error);
            });
        });
      }
    })
    .catch((error) => {
      console.log(error);
      // Handle errors here      if (error.response.data.errors) {
      if (error.response.data.errors) {
        error.response.data.errors.forEach((error) => {
          new Notify({
            status: "error",
            title: "Kiểm tra lại đầu vào",
            text: error,
            effect: "fade",
            speed: 300,
            customClass: "",
            customIcon: "",
            showIcon: true,
            showCloseButton: true,
            autoclose: true,
            autotimeout: 3000,
            gap: 20,
            distance: 20,
            type: 1,
            position: "right top",
            customWrapper: "",
          });
        });
      } else if (error.response.data.message) {
        new Notify({
          status: "error",
          title: "Kiểm tra lại đầu vào",
          text: error.response.data.message,
          effect: "fade",
          speed: 300,
          customClass: "",
          customIcon: "",
          showIcon: true,
          showCloseButton: true,
          autoclose: true,
          autotimeout: 3000,
          gap: 20,
          distance: 20,
          type: 1,
          position: "right top",
          customWrapper: "",
        });
      }
      console.error("Error submitting form:", error);
    });
}

function removeImageProductDetails(id) {}
function removeImageProduct(element) {
  var parentDiv = element.parentElement;
  parentDiv.remove();

  // You can also send an AJAX request to delete the image on the server
  // Example using fetch API:
  // fetch('/admin/product/remove/5/image/24', { method: 'GET' })
  //     .then(response => {
  //         if (response.ok) {
  //             console.log('Image deleted successfully');
  //         } else {
  //             console.error('Failed to delete image');
  //         }
  //     })
  //     .catch(error => console.error('Error:', error));
}
