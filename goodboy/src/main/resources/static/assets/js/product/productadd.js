const formaddproduct = document.getElementById("formaddproudct");
const nameInput = formaddproduct.querySelector("#productname");
const loaisanphamInput = formaddproduct.querySelector("#loaisanphamInput");
const nguongocInput = formaddproduct.querySelector("#nguongocInput");
const thuonghieuInput = formaddproduct.querySelector("#thuonghieuInput");
const chatlieuInput = formaddproduct.querySelector("#chatlieuInput");
const kieudangInput = formaddproduct.querySelector("#kieudangInput");
const productdetailAdd = formaddproduct.querySelector("#productdetailAdd");
const hoavanmuladd = document.getElementById("hoavanmuladd");
const kichthuocmuladd = document.getElementById("kichthuocmuladd");
const listhoavan = [];
// lay data form backend
function getLoaiSanPham() {
  axios
    .get("/admin/managerproduct/category")
    .then(function (response) {
      for (var i = 0; i < response.data.length; i++) {
        const newOption = document.createElement("option");
        newOption.value = response.data[i].id;
        newOption.text = response.data[i].name;
        loaisanphamInput.appendChild(newOption);
      }
    })
    .catch(function (error) {
      console.error("Error getting loaisanpham:", error);
    });
}
function getNguonGoc() {
  axios
    .get("/admin/managerproduct/origin")
    .then(function (response) {
      for (var i = 0; i < response.data.length; i++) {
        const newOption = document.createElement("option");
        newOption.value = response.data[i].id;
        newOption.text = response.data[i].name;
        nguongocInput.appendChild(newOption);
      }
    })
    .catch(function (error) {
      console.error("Error getting nguongoc:", error);
    });
}
function getThuongHieu() {
  axios
    .get("/admin/managerproduct/brand")
    .then(function (response) {
      for (var i = 0; i < response.data.length; i++) {
        const newOption = document.createElement("option");
        newOption.value = response.data[i].id;
        newOption.text = response.data[i].name;
        thuonghieuInput.appendChild(newOption);
      }
    })
    .catch(function (error) {
      console.error("Error getting thuonghieu:", error);
    });
}
function getChatLieu() {
  axios
    .get("/admin/managerproduct/material")
    .then(function (response) {
      for (var i = 0; i < response.data.length; i++) {
        const newOption = document.createElement("option");
        newOption.value = response.data[i].id;
        newOption.text = response.data[i].name;
        chatlieuInput.appendChild(newOption);
      }
    })
    .catch(function (error) {
      console.error("Error getting chatlieu:", error);
    });
}
function getKieuDang() {
  axios
    .get("/admin/managerproduct/styles")
    .then(function (response) {
      for (var i = 0; i < response.data.length; i++) {
        const newOption = document.createElement("option");
        newOption.value = response.data[i].id;
        newOption.text = response.data[i].name;
        kieudangInput.appendChild(newOption);
      }
    })
    .catch(function (error) {
      console.error("Error getting chatlieu:", error);
    });
}
function getHoaVan() {
  axios
    .get("/admin/managerproduct/pattern")
    .then(function (response) {
      for (var i = 0; i < response.data.length; i++) {
        const newCheckbox = document.createElement("input");
        newCheckbox.type = "checkbox";
        newCheckbox.classList.add("form-check-input", "hoavanchoose");
        newCheckbox.setAttribute("nametext", response.data[i].name);
        newCheckbox.id = "gridCheck" + response.data[i].id; // Use a unique ID based on the response data
        newCheckbox.value = response.data[i].id;
        const newLabel = document.createElement("label");
        newLabel.classList.add("form-check-label");
        newLabel.setAttribute("for", "gridCheck" + response.data[i].id); // Match the checkbox ID
        newLabel.textContent = response.data[i].name;
        const newDiv = document.createElement("div");
        newDiv.classList.add("form-check", "col-sm-6");
        newDiv.appendChild(newCheckbox);
        newDiv.appendChild(newLabel);
        hoavanmuladd.appendChild(newDiv);
      }
    })
    .catch(function (error) {
      console.error("Error getting chatlieu:", error);
    });
}
function getKichTuocMulAdd() {
  axios
    .get("/admin/managerproduct/size")
    .then(function (response) {
      for (var i = 0; i < response.data.length; i++) {
        const newCheckbox = document.createElement("input");
        newCheckbox.type = "checkbox";
        newCheckbox.classList.add("form-check-input", "sizemulchoose");
        newCheckbox.setAttribute("nametext", response.data[i].name);
        newCheckbox.id = "sizemulCheck" + response.data[i].id;
        newCheckbox.value = response.data[i].id;

        const newLabel = document.createElement("label");
        newLabel.classList.add("form-check-label");
        newLabel.setAttribute("for", "sizemulCheck" + response.data[i].id);
        newLabel.textContent = response.data[i].name;

        const newDiv = document.createElement("div");
        newDiv.classList.add("form-check", "col-sm-6");
        newDiv.appendChild(newCheckbox);
        newDiv.appendChild(newLabel);
        kichthuocmuladd.appendChild(newDiv);
      }
    })
    .catch(function (error) {
      console.error("Error getting data:", error);
    });
}
async function getKichThuoc(kichthuocSelect) {
  await axios
    .get("/admin/managerproduct/size")
    .then(function (response) {
      for (var i = 0; i < response.data.length; i++) {
        const newOption = document.createElement("option");
        newOption.value = response.data[i].id;
        newOption.text = response.data[i].name;
        kichthuocSelect.appendChild(newOption);
      }
    })
    .catch(function (error) {
      console.error("Error getting size:", error);
    });
}
async function getKichThuocSelected(kichthuocSelect, selected) {
  await axios
    .get("/admin/managerproduct/size")
    .then(function (response) {
      for (var i = 0; i < response.data.length; i++) {
        const newOption = document.createElement("option");
        newOption.value = response.data[i].id;
        newOption.text = response.data[i].name;
        if (selected == response.data[i].id) {
          newOption.selected = true;
        }
        kichthuocSelect.appendChild(newOption);
      }
    })
    .catch(function (error) {
      console.error("Error getting size:", error);
    });
}
// add new properties by form properties
function AddLoaiSanPham(event) {
  event.preventDefault();
  var formData = new FormData(document.getElementById("addLoaiSanPham"));
  axios
    .post("/admin/managerproduct/addcategory", formData)
    .then(function (response) {
      $("#modelAddLoaiSanPham").modal("hide");
      const newOption = document.createElement("option");
      newOption.value = response.data.id;
      newOption.text = response.data.name;
      loaisanphamInput.appendChild(newOption);
      new Notify({
        status: "success",
        title: "Thêm thành công !!!",
        text: `Thêm "${response.data.name}" thành công !!!`,
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
      console.error("Error adding addLoaiSanPham:", error);
    });
}
function AddNguonGoc(event) {
  event.preventDefault();
  var formData = new FormData(document.getElementById("addNguonGoc"));
  axios
    .post("/admin/managerproduct/addorigin", formData)
    .then(function (response) {
      $("#modelAddNguonGoc").modal("hide");
      const newOption = document.createElement("option");
      newOption.value = response.data.id;
      newOption.text = response.data.name;
      nguongocInput.appendChild(newOption);
      new Notify({
        status: "success",
        title: "Thêm thành công !!!",
        text: `Thêm "${response.data.name}" thành công !!!`,
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
      console.error("Error adding origin:", error);
    });
}
function AddThuongHieu(event) {
  event.preventDefault();
  var formData = new FormData(document.getElementById("addThonghieu"));
  axios
    .post("/admin/managerproduct/addbrand", formData)
    .then(function (response) {
      $("#modelAddThonghieu").modal("hide");
      const newOption = document.createElement("option");
      newOption.value = response.data.id;
      newOption.text = response.data.name;
      thuonghieuInput.appendChild(newOption);
      new Notify({
        status: "success",
        title: "Thêm thành công !!!",
        text: `Thêm "${response.data.name}" thành công !!!`,
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
      console.error("Error adding addThonghieu:", error);
    });
}
function AddChatLieu(event) {
  event.preventDefault();
  var formData = new FormData(document.getElementById("addChatLieu"));
  axios
    .post("/admin/managerproduct/addmaterial", formData)
    .then(function (response) {
      $("#modelAddChatLieu").modal("hide");
      const newOption = document.createElement("option");
      newOption.value = response.data.id;
      newOption.text = response.data.name;
      chatlieuInput.appendChild(newOption);
      new Notify({
        status: "success",
        title: "Thêm thành công !!!",
        text: `Thêm "${response.data.name}" thành công !!!`,
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
      console.error("Error adding material:", error);
    });
}
function AddKieuDang(event) {
  event.preventDefault();
  var formData = new FormData(document.getElementById("addkieudang"));
  axios
    .post("/admin/managerproduct/addstyles", formData)
    .then(function (response) {
      $("#modelAddKieuDang").modal("hide");
      const newOption = document.createElement("option");
      newOption.value = response.data.id;
      newOption.text = response.data.name;
      kieudangInput.appendChild(newOption);
      new Notify({
        status: "success",
        title: "Thêm thành công !!!",
        text: `Thêm "${response.data.name}" thành công !!!`,
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
      console.error("Error adding addkieudang:", error);
    });
}
function AddHoaVan(event) {
  event.preventDefault();
  var formData = new FormData(document.getElementById("addHoaVan"));
  axios
    .post("/admin/managerproduct/addpattern", formData)
    .then(function (response) {
      $("#hoavanadd").modal("show");
      $("#formAddHoaVan").modal("hide");
      const newCheckbox = document.createElement("input");
      newCheckbox.type = "checkbox";
      newCheckbox.setAttribute("nametext", response.data.name);
      newCheckbox.classList.add("form-check-input", "hoavanchoose");
      newCheckbox.id = "gridCheck" + response.data.id; // Use a unique ID based on the response data
      newCheckbox.value = response.data.id;
      const newLabel = document.createElement("label");
      newLabel.classList.add("form-check-label");
      newLabel.setAttribute("for", "gridCheck" + response.data.id); // Match the checkbox ID
      newLabel.textContent = response.data.name;
      const newDiv = document.createElement("div");
      newDiv.classList.add("form-check", "col-sm-6");
      newDiv.appendChild(newCheckbox);
      newDiv.appendChild(newLabel);
      hoavanmuladd.appendChild(newDiv);
      new Notify({
        status: "success",
        title: "Thêm thành công !!!",
        text: `Thêm "${response.data.name}" thành công !!!`,
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
      console.error("Error adding HoaVan:", error);
    });
}

function AddKichThuoc(event) {
  event.preventDefault();
  var formData = new FormData(document.getElementById("addKichThuoc"));
  axios
    .post("/admin/managerproduct/addsize", formData)
    .then(function (response) {
      const newCheckbox = document.createElement("input");
      newCheckbox.type = "checkbox";
      newCheckbox.classList.add("form-check-input", "sizemulchoose");
      newCheckbox.setAttribute("nametext", response.data.name);
      newCheckbox.id = "sizemulCheck" + response.data.id;
      newCheckbox.value = response.data.id;

      const newLabel = document.createElement("label");
      newLabel.classList.add("form-check-label");
      newLabel.setAttribute("for", "sizemulCheck" + response.data.id);
      newLabel.textContent = response.data.name;

      const newDiv = document.createElement("div");
      newDiv.classList.add("form-check", "col-sm-6");
      newDiv.appendChild(newCheckbox);
      newDiv.appendChild(newLabel);
      kichthuocmuladd.appendChild(newDiv);
      $("#modelAddKichThuoc").modal("hide");
      new Notify({
        status: "success",
        title: "Thêm thành công !!!",
        text: `Thêm "${response.data.name}" thành công !!!`,
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
      const listKichThuoc = formaddproduct.querySelectorAll(".kichthuocselect");
      listKichThuoc.forEach((element) => {
        const newOption = document.createElement("option");
        newOption.value = response.data.id;
        newOption.text = response.data.name;
        element.appendChild(newOption);
      });
      1;
    })
    .catch(function (error) {
      console.error("Error adding KichThuoc:", error);
    });
}
function OpenModalAddKichThuoc() {
  $("#modelAddKichThuoc").modal("show");
}
// function to handle submit form
function submitForm(event) {
  event.preventDefault();
  var errorcount = 0;
  const productName = nameInput.value.trim();
  resetErrorElement(nameInput);
  if (productName === "") {
    errorcount++;
    setErrorElement(nameInput, "Tên không được để trống");
  }
  const loaiSanPham = loaisanphamInput.value;
  resetErrorElement(loaisanphamInput);
  if (loaiSanPham == -1) {
    errorcount++;
    setErrorElement(
      loaisanphamInput,
      "Loại sản phẩm không được trống để trống"
    );
  }
  const nguonGoc = nguongocInput.value;
  resetErrorElement(nguongocInput);
  if (nguonGoc == -1) {
    errorcount++;
    setErrorElement(nguongocInput, "Nguồn gốc không được trống để trống");
  }
  const thuongHieu = thuonghieuInput.value;
  resetErrorElement(thuonghieuInput);
  if (thuongHieu == -1) {
    errorcount++;
    setErrorElement(thuonghieuInput, "Thương hiệu không được trống để trống");
  }
  const chatLieu = chatlieuInput.value;
  resetErrorElement(chatlieuInput);
  if (chatLieu == -1) {
    errorcount++;
    setErrorElement(chatlieuInput, "Chất liệu không được trống để trống");
  }
  const kieuDang = kieudangInput.value;
  resetErrorElement(kieudangInput);
  if (kieuDang == -1) {
    errorcount++;
    setErrorElement(kieudangInput, "Kiểu dáng không được trống để trống");
  }
  const hoavanDetails = [];
  const hoavanElements = document.querySelectorAll(".productdetail");

  hoavanElements.forEach(function (element) {
    const patternName = element.getAttribute("idhoavan");
    const size = element.querySelector('select[name="khichthuoc"]');
    resetErrorElement(size);
    if (size.value == -1) {
      errorcount++;
      setErrorElement(size, "Kích thước không được trống để trống");
    }
    const quantity = element.querySelector('input[name="soluong"]');
    resetErrorElement(quantity);
    if (quantity.value < 0) {
      errorcount++;
      setErrorElement(quantity, "Số lượng không thểm âm");
    }
    const price = element.querySelector('input[name="gia"]');
    resetErrorElement(price);
    if (price.value == -1) {
      errorcount++;
      setErrorElement(price, "Giá không thểm âm");
    }
    const description = element.querySelector('textarea[name="mota"]').value;
    hoavanDetails.push({
      idPattern: patternName,
      idSize: size.value,
      quantity: quantity.value,
      price: price.value,
      description: description,
    });
  });
  if (errorcount > 0) {
    $("#modalThemThatBai").modal("show");
    return false;
  } else {
    const productAddRequest = {
      name: productName,
      idCategory: loaiSanPham,
      idOrigin: nguonGoc,
      idBrand: thuongHieu,
      idMaterial: chatLieu,
      idStyles: kieuDang,
      productDetails: hoavanDetails,
    };
    console.log(productAddRequest);
    axios
      .post("/admin/managerproduct/saveproduct", productAddRequest)
      .then((response) => {
        $("#modelThemProductThanhCong").modal("show");
        console.log("Response:", response.data);
        if (response.data.id) {
          const inputImageProductDetails = document.querySelectorAll(
            "input[type='file'].hoavanimages"
          );

          const uploadPromises = [];

          inputImageProductDetails.forEach((element) => {
            const files = element.files;
            const idhoavan = element.getAttribute("idhoavan");

            Array.from(files).forEach((file) => {
              const formData = new FormData();
              formData.append("file", file);
              formData.append("idhoavan", idhoavan);
              formData.append("idproduct", response.data.id);

              const uploadPromise = axios.post(
                "/admin/managerproduct/product/image/productdetails",
                formData,
                {
                  headers: {
                    "Content-Type": "multipart/form-data",
                  },
                }
              );

              uploadPromises.push(uploadPromise);
            });
          });

          Promise.all(uploadPromises)
            .then((uploadResponses) => {
              uploadResponses.forEach((uploadResponse) => {
                console.log("Upload Response:", uploadResponse);

                new Notify({
                  status: "success",
                  title: "Thành công",
                  text: "Thêm ảnh thành công",
                  effect: "fade",
                  speed: 300,
                  customClass: "",
                  customIcon: `<img src="${uploadResponse.data}" alt="Ảnh sản phẩm" class="img-fluid" style="height: 30px;">`,
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
            })
            .catch((uploadError) => {
              console.error("Upload Error:", uploadError);
            });
        }
      })
      .catch((error) => {
        $("#modalThemThatBai").modal("show");
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
}
// Function to handle UI product detail form
function getAllHoaVanChoose() {
  const checkboxes = hoavanmuladd.querySelectorAll(".hoavanchoose");
  checkboxes.forEach((checkbox) => {
    if (checkbox.checked) {
      checkbox.disabled = true;
      if (!listhoavan.includes(checkbox.value)) {
        listhoavan.push(checkbox.value);
        text = checkbox.getAttribute("nametext");
        addNewDivHoaVan(`${checkbox.value}`, text, getAllSizeChoose());
      }
    } else {
      checkbox.disabled = false;
      const index = listhoavan.indexOf(checkbox.value);
      if (index !== -1) {
        listhoavan.splice(index, 1);
      }
    }
  });
  removeSizeChoose();
  return listhoavan;
}
function getAllSizeChoose() {
  const checkboxes = kichthuocmuladd.querySelectorAll(".sizemulchoose");
  let listsize = [];
  checkboxes.forEach((checkbox) => {
    if (checkbox.checked) {
      checkbox.disabled = true;
      listsize.push(checkbox.value);
    } else {
      checkbox.disabled = false;
      const index = listsize.indexOf(checkbox.value);
      if (index !== -1) {
        listsize.splice(index, 1);
      }
    }
  });
  return listsize;
}
function removeHoaVanChoose(idHoaVan) {
  // Remove the specified idHoaVan from the listhoavan array
  const index = listhoavan.indexOf(idHoaVan);
  if (index !== -1) {
    listhoavan.splice(index, 1);
  }
  // Enable the corresponding checkbox
  const checkbox = hoavanmuladd.querySelector(
    `.hoavanchoose[value="${idHoaVan}"]`
  );
  if (checkbox) {
    checkbox.disabled = false;
    checkbox.checked = false; // Uncheck the checkbox if needed
    document.getElementById(`hoavan${idHoaVan}`).remove();
  }
  return listhoavan;
}
function removeSizeChoose() {
  const checkboxes = kichthuocmuladd.querySelectorAll(`input.sizemulchoose`);
  checkboxes.forEach((element) => {
    if (element) {
      element.disabled = false;
      element.checked = false; // Uncheck the checkbox if needed
    }
  });
}
async function addNewDivHoaVan(idhoavan, hoavanname, sizeSelects) {
  const hoavanexits = document.getElementById(idhoavan);
  if (hoavanexits) {
    return;
  }
  // Create a new div element
  const newDiv = document.createElement("div");
  newDiv.classList.add("parrtenadd"); // Add the class 'parrtenadd' to the new div
  newDiv.id = `hoavan${idhoavan}`; // Set the ID of the new div
  // Set the HTML content for the new div
  newDiv.innerHTML = `
    <hr>
    <div class="hoavannamemb-3 mb-2 d-flex align-items-center">Kiêu Hoa Văn: <span class="fw-bold" id="patternname">${hoavanname}</span><button
        class="btn btn-secondary mx-2" type="button" onclick="addNewFormProductDetail('${idhoavan}')">Thêm</button>
        <button
        class="btn btn-danger mx-2" type="button" onclick="removeHoaVanChoose('${idhoavan}')">Xóa</button>
        <div>
        <input class="form-control hoavanimages" type="file" idhoavan="${idhoavan}" id="formFileMultiple" multiple />
        </div>
        </div>
  `;
  // Append the new div to the 'productdetailAdd' element
  productdetailAdd.appendChild(newDiv);
  if (!sizeSelects || sizeSelects.length === 0) {
    addNewFormProductDetail(`${idhoavan}`);
  } else {
    sizeSelects.forEach(async (size) => {
      await addNewFormProductDetailKichThuoc(`${idhoavan}`, size);
    });
  }
}
async function addNewFormProductDetail(idhoavan) {
  // Create a new div element for the product detail form
  const newFormDiv = document.createElement("div");
  newFormDiv.classList.add("row", "border", "py-3", "mb-3", "productdetail");
  newFormDiv.setAttribute("idhoavan", idhoavan);
  // HTML content for the product detail form
  newFormDiv.innerHTML = `
                      <div class="col-6 row mb-3">
                      <label for="inputEmail3" class="col-sm-3 col-form-label">Kích thước</label>
                      <div class="col-sm-6">
                        <select class="form-select kichthuocselect" aria-label="Default select example" name="khichthuoc" id="kichthuocInput">
                          <option value="-1" selected="selected" disabled > Chọn kích thước </option>
                        </select>
                      </div>
                      <div class="col-sm-3">
                      <button type="button" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#modelAddKichThuoc">
                        Thêm</button>
                      </div>
                    </div>
                    <div class="col-6 row mb-3">
                      <label for="inputNumber" class="col-sm-4 col-form-label">Số lượng</label>
                      <div class="col-sm-6">
                        <input type="number" class="form-control" value=10 min=0 name="soluong">
                      </div>
                    </div>
                    <div class="col-6 row mb-3">
                      <label for="inputNumber" class="col-sm-3 col-form-label">Giá</label>
                      <div class="col-sm-9">
                        <div class="input-group mb-3">
                          <span class="input-group-text">đ</span>
                          <input type="text" class="form-control" value=100000 min=0  name="gia" aria-label="Amount (to the nearest dollar)">
                          <span class="input-group-text">Vnđ</span>
                        </div>
                      </div>
                    </div>
                    <div class="form-floating mb-3 col-12">
                      <textarea class="form-control" placeholder="Leave a comment here" id="floatingTextarea"
                        style="height: 18px;" name="mota"></textarea>
                      <label for="floatingTextarea">Mô tả</label>
                    </div>
                    <div class="col-2">
                      <button class="btn btn-danger" onclick="removeFormProductDetails(event)">Xóa</button>
                    </div>
  `;
  const targetElement = document.getElementById(`hoavan${idhoavan}`);
  targetElement.appendChild(newFormDiv);
  const kichthuocselect = newFormDiv.querySelector(".kichthuocselect");
  await getKichThuoc(kichthuocselect);
  await checkHoaVanSeleted(`hoavan${idhoavan}`);
}
async function addNewFormProductDetailKichThuoc(idhoavan, idkichthuocselect) {
  // Create a new div element for the product detail form
  const newFormDiv = document.createElement("div");
  newFormDiv.classList.add("row", "border", "py-3", "mb-3", "productdetail");
  newFormDiv.setAttribute("idhoavan", idhoavan);
  // HTML content for the product detail form
  newFormDiv.innerHTML = `
                      <div class="col-6 row mb-3">
                      <label for="inputEmail3" class="col-sm-3 col-form-label">Kích thước</label>
                      <div class="col-sm-6">
                        <select class="form-select kichthuocselect" aria-label="Default select example" name="khichthuoc" id="kichthuocInput" onchange="checkHoaVanSeleted('hoavan${idhoavan}')">
                          <option value="-1" selected="selected" disabled > Chọn kích thước </option>
                        </select>
                      </div>
                      <div class="col-sm-3">
                      <button type="button" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#modelAddKichThuoc">
                        Thêm</button>
                      </div>
                    </div>
                    <div class="col-6 row mb-3">
                      <label for="inputNumber" class="col-sm-4 col-form-label">Số lượng</label>
                      <div class="col-sm-6">
                        <input type="number" class="form-control" value=10 min=0 name="soluong">
                      </div>
                    </div>
                    <div class="col-6 row mb-3">
                      <label for="inputNumber" class="col-sm-3 col-form-label">Giá</label>
                      <div class="col-sm-9">
                        <div class="input-group mb-3">
                          <span class="input-group-text">đ</span>
                          <input type="text" class="form-control" value=100000 min=0  name="gia" aria-label="Amount (to the nearest dollar)">
                          <span class="input-group-text">Vnđ</span>
                        </div>
                      </div>
                    </div>
                    <div class="form-floating mb-3 col-12">
                      <textarea class="form-control" placeholder="Leave a comment here" id="floatingTextarea"
                        style="height: 18px;" name="mota"></textarea>
                      <label for="floatingTextarea">Mô tả</label>
                    </div>
                    <div class="col-2">
                      <button class="btn btn-danger" onclick="removeFormProductDetails(event)">Xóa</button>
                    </div>
  `;
  const targetElement = document.getElementById(`hoavan${idhoavan}`);
  targetElement.appendChild(newFormDiv);
  const kichthuocselect = newFormDiv.querySelector(".kichthuocselect");
  await getKichThuocSelected(kichthuocselect, idkichthuocselect);
  await checkHoaVanSeleted(`hoavan${idhoavan}`);
}
// Add a click event listener to the "Xóa" button
function removeFormProductDetails(event) {
  const target = event.target;
  if (target.classList.contains("btn-danger")) {
    removeProductDetail(target);
  }
}
function checkHoaVanDivExits(idhoavan) {
  const hoavanexits = document.getElementById(idhoavan);
  if (hoavanexits) {
    return true;
  }
  return false;
}

// function to handle set message
function setErrorElement(element, message) {
  resetErrorElement(element);
  const parentnote = element.parentNode;
  const errormessage = document.createElement("p");
  errormessage.classList.add("error");
  errormessage.classList.add("text-danger");
  errormessage.textContent = message;
  parentnote.appendChild(errormessage);
}
function resetErrorElement(element) {
  const parentnote = element.parentNode;
  const errormessage = parentnote.querySelector(".error");
  if (errormessage) {
    errormessage.remove();
  }
}
function init() {
  getHoaVan();
  getKichTuocMulAdd();
  getLoaiSanPham();
  getNguonGoc();
  getThuongHieu();
  getChatLieu();
  getKieuDang();
}
async function checkHoaVanSeleted(idhoavan) {
  const hoavandivCheck = document.getElementById(idhoavan);
  const allSizeSelects = hoavandivCheck.querySelectorAll(
    'select[name="khichthuoc"]'
  );
  const allSizeValues = Array.from(allSizeSelects).map(
    (sizeSelect) => sizeSelect.value
  );
  allSizeSelects.forEach((sizeSelect) => {
    Array.from(sizeSelect.options).forEach((option) => {
      option.disabled =
        allSizeValues.includes(option.value) || option.value == -1;
    });
  });
}
init();

//
