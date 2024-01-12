// Define your object
class SearchFilter {
  constructor() {
    this.pageno = 1;
    this.rowcount = 10;
    this.sortBy = "createdAt";
    this.sortDir = false;
    this.txtSearch = "";
    this.idOrigins = [];
    this.idBrands = [];
    this.idMaterials = [];
    this.idStyless = [];
    this.idCategorys = [];
  }
  // Add a method to update properties
  updateFilter(newFilter) {
    if (newFilter.hasOwnProperty("txtSearch")) {
      this.txtSearch = newFilter.txtSearch;
    }

    if (newFilter.hasOwnProperty("idOrigins")) {
      this.idOrigins = newFilter.idOrigins;
    }
    if (newFilter.hasOwnProperty("pageno")) {
      this.pageno = newFilter.pageno;
    }
    if (newFilter.hasOwnProperty("rowcount")) {
      this.rowcount = newFilter.rowcount;
    }
    if (newFilter.hasOwnProperty("sortBy")) {
      this.sortBy = newFilter.sortBy;
    }
    if (newFilter.hasOwnProperty("sortDir")) {
      this.sortDir = newFilter.sortDir;
    }
    if (newFilter.hasOwnProperty("idBrands")) {
      this.idBrands = newFilter.idBrands;
    }

    if (newFilter.hasOwnProperty("idMaterials")) {
      this.idMaterials = newFilter.idMaterials;
    }

    if (newFilter.hasOwnProperty("idStyless")) {
      this.idStyless = newFilter.idStyless;
    }

    if (newFilter.hasOwnProperty("idCategorys")) {
      this.idCategorys = newFilter.idCategorys;
    }
  }
}
const productview = document.querySelector("#productview");
const brandAll = document.querySelector("#brand-all");
const materialAll = document.querySelector("#material-all");
const originAll = document.querySelector("#origin-all");
const stylesAll = document.querySelector("#styles-all");
const panigation = document.querySelector("#panigation");
const textSearch = document.querySelector("#text-search");
const idBrands = document.querySelectorAll("input[name='idBrands']");
const idMaterials = document.querySelectorAll("input[name='idMaterials']");
const idStyless = document.querySelectorAll("input[name='idStyless']");
const idCategorys = document.querySelectorAll("input[name='idCategorys']");
const idOrigins = document.querySelectorAll("input[name='idOrigins']");

var searchFilter = new SearchFilter();

const productExample = {
  id: 1,
  code: "PD3",
  idOrigin: {
    id: 1,
    code: "ON3",
    name: "Hàn Quốc",
    createdAt: "2023-11-08T00:00:00",
    updatedAt: "2023-11-08T00:00:00",
    deleted: false,
    status: 1,
  },
  idBrand: {
    id: 1,
    code: "BR3",
    name: "Jussy",
    createdAt: "2023-11-08T00:00:00",
    updatedAt: "2023-11-08T00:00:00",
    deleted: false,
    status: 1,
  },
  idMaterial: {
    id: 1,
    code: "ML3",
    name: "Lụa",
    createdAt: "2023-11-08T00:00:00",
    updatedAt: "2023-11-08T00:00:00",
    deleted: false,
    status: 1,
  },
  idCategory: {
    id: 1,
    code: "CA1",
    name: "Khăn choàng thu đông",
    createdAt: "2023-11-08T00:00:00",
    updatedAt: "2023-11-08T00:00:00",
    deleted: false,
    status: 1,
  },
  idStyles: {
    id: 1,
    code: "STY1",
    name: "Bohemian",
    createdAt: "2023-11-08T00:00:00",
    updatedAt: "2023-11-08T00:00:00",
    deleted: false,
    status: 1,
  },
  idColor: {
    id: 1,
    code: "CL2",
    name: "Đen",
    createdAt: "2023-11-08T00:00:00",
    updatedAt: "2023-11-08T00:00:00",
    deleted: false,
    status: 1,
  },
  name: "Khăn quàng cổ Mohair",
  createdAt: "2023-11-08T00:00:00",
  updatedAt: "2023-12-22T18:13:24.18",
  deleted: false,
  status: 1,
  imageProducts: [
    {
      id: 8,
      img: "https://res.cloudinary.com/da30qcqmf/image/upload/v1699554130/DUANTOTNGHIEP/5e31a335b42fae0d371c2220be0373cf_rbowby.jpg",
      createdAt: "2023-11-08T00:00:00",
      updatedAt: "2023-11-08T00:00:00",
      status: 1,
      deleted: false,
    },
    {
      id: 9,
      img: "https://res.cloudinary.com/da30qcqmf/image/upload/v1699554130/DUANTOTNGHIEP/5e31a335b42fae0d37ss1c2220be0373_os2nel.jpg",
      createdAt: "2023-11-08T00:00:00",
      updatedAt: "2023-11-08T00:00:00",
      status: 1,
      deleted: false,
    },
    {
      id: 10,
      img: "https://res.cloudinary.com/da30qcqmf/image/upload/v1699554130/DUANTOTNGHIEP/5e31a335b42fae0d32c2220be0373cf_peg2pl.jpg",
      createdAt: "2023-11-08T00:00:00",
      updatedAt: "2023-11-08T00:00:00",
      status: 1,
      deleted: false,
    },
    {
      id: 11,
      img: "https://res.cloudinary.com/da30qcqmf/image/upload/v1699554130/DUANTOTNGHIEP/5e31a335b42fae0d371c22210be0373cf_ndblty.jpg",
      createdAt: "2023-11-08T00:00:00",
      updatedAt: "2023-11-08T00:00:00",
      status: 1,
      deleted: false,
    },
  ],
  maxprice: 250000.0,
  minprice: 250000.0,
  productdetails: [
    [
      {
        id: 14,
        code: "PDD5",
        name: "Khăn quàng cổ Mohair",
        price: 250000.0,
        quantity: 199,
        description:
          "NOTE:  MÀU SẮC CÓ THỂ THAY ĐỔI 1 CHÚT DO ÁNH SÁNG KHI CHỤP, ĐỘ HIỂN THỊ MÀN HÌNH VÀ TÙY TỪNG CÂY VẢI MỖI ĐỢT HÀNG VỀ!_x000D_",
        idSize: {
          id: 1,
          code: "SZ1",
          name: "90*180cm",
          createdAt: "2023-11-08T00:00:00",
          updatedAt: "2023-11-08T00:00:00",
          status: 1,
          deleted: false,
        },
        idPattern: {
          id: 1,
          code: "PN1",
          name: "Trơn",
          createdAt: "2023-11-08T00:00:00",
          updatedAt: "2023-11-08T00:00:00",
          deleted: false,
          status: 1,
        },
        status: 1,
        createdAt: "2023-11-08T00:00:00",
        updatedAt: "2023-12-24T00:01:32.393",
        deleted: false,
        imageProducts: [
          {
            id: 10021,
            img: "http://res.cloudinary.com/da30qcqmf/image/upload/v1703350908/DUANTOTNGHIEP/19dcec42-49dc-4c1f-8bae-dc784a159789.png",
            createdAt: "2023-12-24T00:01:48.447",
            updatedAt: "2023-12-24T00:01:48.447",
            status: 1,
            deleted: false,
          },
          {
            id: 10022,
            img: "http://res.cloudinary.com/da30qcqmf/image/upload/v1703350909/DUANTOTNGHIEP/d523bfc2-6d92-470f-8625-292a0dd62a95.png",
            createdAt: "2023-12-24T00:01:49.437",
            updatedAt: "2023-12-24T00:01:49.437",
            status: 1,
            deleted: false,
          },
        ],
        firstImage:
          "http://res.cloudinary.com/da30qcqmf/image/upload/v1703350908/DUANTOTNGHIEP/19dcec42-49dc-4c1f-8bae-dc784a159789.png",
        nameProduct: "Khăn quàng cổ Mohair",
      },
    ],
    [
      {
        id: 15,
        code: "PDD6",
        name: "Khăn quàng cổ Mohair",
        price: 250000.0,
        quantity: 196,
        description:
          "NOTE:  MÀU SẮC CÓ THỂ THAY ĐỔI 1 CHÚT DO ÁNH SÁNG KHI CHỤP, ĐỘ HIỂN THỊ MÀN HÌNH VÀ TÙY TỪNG CÂY VẢI MỖI ĐỢT HÀNG VỀ!_x000D_",
        idSize: {
          id: 2,
          code: "SZ2",
          name: "130*180cm",
          createdAt: "2023-11-08T00:00:00",
          updatedAt: "2023-11-08T00:00:00",
          status: 1,
          deleted: false,
        },
        idPattern: {
          id: 2,
          code: "PN2",
          name: "In hoa",
          createdAt: "2023-11-08T00:00:00",
          updatedAt: "2023-11-08T00:00:00",
          deleted: false,
          status: 1,
        },
        status: 1,
        createdAt: "2023-11-08T00:00:00",
        updatedAt: "2023-12-04T14:39:57.467",
        deleted: false,
        imageProducts: [],
        firstImage:
          "https://res.cloudinary.com/da30qcqmf/image/upload/v1699554130/DUANTOTNGHIEP/5e31a335b42fae0d371c2220be0373cf_rbowby.jpg",
        nameProduct: "Khăn quàng cổ Mohair",
      },
    ],
    [
      {
        id: 16,
        code: "PDD7",
        name: "Khăn quàng cổ Mohair",
        price: 250000.0,
        quantity: 195,
        description:
          "NOTE:  MÀU SẮC CÓ THỂ THAY ĐỔI 1 CHÚT DO ÁNH SÁNG KHI CHỤP, ĐỘ HIỂN THỊ MÀN HÌNH VÀ TÙY TỪNG CÂY VẢI MỖI ĐỢT HÀNG VỀ!_x000D_",
        idSize: {
          id: 3,
          code: "SZ3",
          name: "72*188cm",
          createdAt: "2023-11-08T00:00:00",
          updatedAt: "2023-11-08T00:00:00",
          status: 1,
          deleted: false,
        },
        idPattern: {
          id: 3,
          code: "PN3",
          name: "Kẻ sọc",
          createdAt: "2023-11-08T00:00:00",
          updatedAt: "2023-11-08T00:00:00",
          deleted: false,
          status: 1,
        },
        status: 1,
        createdAt: "2023-11-08T00:00:00",
        updatedAt: "2023-12-04T14:39:57.4",
        deleted: false,
        imageProducts: [],
        firstImage:
          "https://res.cloudinary.com/da30qcqmf/image/upload/v1699554130/DUANTOTNGHIEP/5e31a335b42fae0d371c2220be0373cf_rbowby.jpg",
        nameProduct: "Khăn quàng cổ Mohair",
      },
    ],
    [
      {
        id: 17,
        code: "PDD8",
        name: "Khăn quàng cổ Mohair",
        price: 250000.0,
        quantity: 200,
        description:
          "NOTE:  MÀU SẮC CÓ THỂ THAY ĐỔI 1 CHÚT DO ÁNH SÁNG KHI CHỤP, ĐỘ HIỂN THỊ MÀN HÌNH VÀ TÙY TỪNG CÂY VẢI MỖI ĐỢT HÀNG VỀ!_x000D_",
        idSize: {
          id: 4,
          code: "SZ4",
          name: "70*70cm",
          createdAt: "2023-11-08T00:00:00",
          updatedAt: "2023-11-08T00:00:00",
          status: 1,
          deleted: false,
        },
        idPattern: {
          id: 4,
          code: "PN4",
          name: "Hình khối",
          createdAt: "2023-11-08T00:00:00",
          updatedAt: "2023-11-08T00:00:00",
          deleted: false,
          status: 1,
        },
        status: 1,
        createdAt: "2023-11-08T00:00:00",
        updatedAt: "2023-11-08T00:00:00",
        deleted: false,
        imageProducts: [],
        firstImage:
          "https://res.cloudinary.com/da30qcqmf/image/upload/v1699554130/DUANTOTNGHIEP/5e31a335b42fae0d371c2220be0373cf_rbowby.jpg",
        nameProduct: "Khăn quàng cổ Mohair",
      },
    ],
  ],
  images: [
    "https://res.cloudinary.com/da30qcqmf/image/upload/v1699554130/DUANTOTNGHIEP/5e31a335b42fae0d371c2220be0373cf_rbowby.jpg",
    "https://res.cloudinary.com/da30qcqmf/image/upload/v1699554130/DUANTOTNGHIEP/5e31a335b42fae0d37ss1c2220be0373_os2nel.jpg",
    "https://res.cloudinary.com/da30qcqmf/image/upload/v1699554130/DUANTOTNGHIEP/5e31a335b42fae0d32c2220be0373cf_peg2pl.jpg",
    "https://res.cloudinary.com/da30qcqmf/image/upload/v1699554130/DUANTOTNGHIEP/5e31a335b42fae0d371c22210be0373cf_ndblty.jpg",
    "http://res.cloudinary.com/da30qcqmf/image/upload/v1703350908/DUANTOTNGHIEP/19dcec42-49dc-4c1f-8bae-dc784a159789.png",
    "http://res.cloudinary.com/da30qcqmf/image/upload/v1703350909/DUANTOTNGHIEP/d523bfc2-6d92-470f-8625-292a0dd62a95.png",
  ],
};
function generateProductDiv(product) {
  const divProduct = document.createElement("div");

  // Split the classes into an array
  const classes = ["col-lg-4", "col-md-6", "col-sm-6", "pb-1"];

  // Add each class individually
  classes.forEach((className) => {
    divProduct.classList.add(className);
  });

  divProduct.innerHTML = `
   <a class="h6 text-decoration-none text-truncate" href="/product/detail/${
        product.id
    }">
     <div class="product-item bg-light mb-4">
      <div class="product-img position-relative overflow-hidden">
          <img class="img-fluid w-100" src="${
            product.images[0]
          }" alt=""  style="max-width: 100%; max-height: 300px; min-width: 150px;">
      </div>
      <div class="text-center py-4">
         ${product.name}
          <div class="d-flex align-items-center justify-content-center mt-2">
              <h5>${formatToVND(product.minprice)} - ${formatToVND(
    product.maxprice
  )}</h5>
          </div>
          <div class="d-flex align-items-center justify-content-center mb-1">
          <span>${renderStars(
            product.averageRating,
            product.evaluates.length
          )}</span>
      </div>
      </div>
    </div>
    </a>
  `;
  return divProduct;
}

async function updateFillter() {
  const checkedBrands = getCheckedValues(idBrands);
  const checkedMaterials = getCheckedValues(idMaterials);
  const checkedStyles = getCheckedValues(idStyless);
  const checkedCategorys = getCheckedValues(idCategorys);
  const checkedidOrigins = getCheckedValues(idOrigins);
  const filterData = {
    txtSearch: textSearch.value,
    idBrands: checkedBrands,
    idMaterials: checkedMaterials,
    idStyless: checkedStyles,
    idCategorys: checkedCategorys,
    idOrigins: checkedidOrigins,
    pageno: 1,
  };
  searchFilter.updateFilter(filterData);
  console.log(searchFilter);
  await getPageProducts();
}
function getCheckedValues(nodeList) {
  const checkedValues = Array.from(nodeList)
    .filter((input) => input.checked)
    .map((input) => input.value);
  if (checkedValues.length > 0) {
    return checkedValues;
  } else {
    return getAllValue(nodeList);
  }
}
function getAllValue(nodeList) {
  const checkedValues = Array.from(nodeList).map((input) => input.value);
  return checkedValues;
}

async function getPageNo(value) {
  searchFilter.updateFilter({
    pageno: value,
  });
  await getPageProducts();
}
async function sortBy(sortBy, sortDir) {
  searchFilter.updateFilter({
    sortBy: sortBy,
    sortDir: sortDir,
  });
  console.log(searchFilter);

  await getPageProducts();
}
async function updateNumBerOfRow(value) {
  searchFilter.updateFilter({
    rowcount: value,
    pageno: 1,
  });
  console.log(searchFilter);
  await getPageProducts();
}
function updateNumBerOfRowUI() {}

async function getPageProducts() {
  try {
    const response = await axios.post(
      "/product/test/rest/getProducts",
      JSON.stringify(searchFilter),
      {
        headers: {
          "Content-type": "application/json; charset=UTF-8",
        },
      }
    );

    console.log("Response Data:", response.data);
    productview.innerHTML = "";
    if (response.data) {
      if (response.data.length > 0) {
        response.data.forEach((product) => {
          productview.appendChild(generateProductDiv(product));
        });
        await updatePanigation();
      }
    } else {
      productview.innerHTML = `<p class="text-center">Không có sản phẩm</p>`;
    }
  } catch (error) {
    console.error("Error:", error.message);
    throw error; // Rethrow the error if needed
  }
}
function init() {
  const checkedBrands = getCheckedValues(idBrands);
  const checkedMaterials = getCheckedValues(idMaterials);
  const checkedStyles = getCheckedValues(idStyless);
  const checkedCategorys = getCheckedValues(idCategorys);
  const checkedidOrigins = getCheckedValues(idOrigins);
  const filterData = {
    txtSearch: textSearch.value,
    idBrands: checkedBrands,
    idMaterials: checkedMaterials,
    idStyless: checkedStyles,
    idCategorys: checkedCategorys,
    idOrigins: checkedidOrigins,
  };
  searchFilter.updateFilter(filterData);
  getPageProducts();
}

async function updatePanigation() {
  try {
    const pani = await axios.post(
      "/product/test/rest/getPanigation",
      JSON.stringify(searchFilter),
      {
        headers: {
          "Content-type": "application/json; charset=UTF-8",
        },
      }
    );
    const totalpage = await axios.post(
      "/product/test/rest/getTotalPage",
      JSON.stringify(searchFilter),
      {
        headers: {
          "Content-type": "application/json; charset=UTF-8",
        },
      }
    );
    panigationUi(pani.data, totalpage.data);
  } catch (error) {
    console.error("Error:", error.message);
    throw error; // Rethrow the error if needed
  }
}
function pre() {
  getPageNo(searchFilter.pageno - 1);
}
function next() {
  getPageNo(searchFilter.pageno + 1);
}
function panigationUi(panigationData, totalpage) {
  panigation.innerHTML = ""; // Clear existing content

  // Previous Button
  const previousButton = document.createElement("li");
  previousButton.classList.add("page-item");
  if (1 === searchFilter.pageno) {
    previousButton.classList.add("disabled");
  }
  previousButton.innerHTML = `<a class="page-link" role="button" onclick="pre()">Previous</a>`;
  panigation.appendChild(previousButton);

  // Page Numbers
  panigationData.forEach((pani) => {
    const pageItem = document.createElement("li");
    pageItem.classList.add("page-item");
    if (searchFilter.pageno === pani) {
      pageItem.classList.add("active"); // Fix: Use pageItem instead of previousButton
    }
    pageItem.innerHTML = `<a class="page-link" role="button" onclick="getPageNo(${pani})" >${pani}</a>`;
    panigation.appendChild(pageItem);
  });

  // Next Button
  const nextButton = document.createElement("li");
  nextButton.classList.add("page-item");
  if (totalpage === searchFilter.pageno) {
    nextButton.classList.add("disabled");
  }
  nextButton.innerHTML = `<a class="page-link" role="button" onclick="next()">Next</a>`;
  panigation.appendChild(nextButton);
}

init();
function formatToVND(amount) {
  const formatter = new Intl.NumberFormat("vi-VN", {
    style: "currency",
    currency: "VND",
    minimumFractionDigits: 0, // Set to 0 to display whole numbers
  });
  return formatter.format(amount);
}

{
  /*
<div class="col-lg-4 col-md-6 col-sm-6 pb-1">
<div class="product-item bg-light mb-4">
    <div class="product-img position-relative overflow-hidden">
        <img class="img-fluid w-100" src="img/product-1.jpg" alt="">
        <div class="product-action">
            <a class="btn btn-outline-dark btn-square" href=""><i
                    class="fa fa-shopping-cart"></i></a>
            <a class="btn btn-outline-dark btn-square" href=""><i class="far fa-heart"></i></a>
            <a class="btn btn-outline-dark btn-square" href=""><i
                    class="fa fa-sync-alt"></i></a>
            <a class="btn btn-outline-dark btn-square" href=""><i class="fa fa-search"></i></a>
        </div>
    </div>
    <div class="text-center py-4">
        <a class="h6 text-decoration-none text-truncate" href="">Product Name Goes Here</a>
        <div class="d-flex align-items-center justify-content-center mt-2">
            <h5>$123.00</h5>
            <h6 class="text-muted ml-2"><del>$123.00</del></h6>
        </div>
        <div class="d-flex align-items-center justify-content-center mb-1">
            <small class="fa fa-star text-primary mr-1"></small>
            <small class="fa fa-star text-primary mr-1"></small>
            <small class="fa fa-star text-primary mr-1"></small>
            <small class="fa fa-star text-primary mr-1"></small>
            <small class="fa fa-star text-primary mr-1"></small>
            <small>(99)</small>
        </div>
    </div>
</div>
</div>
 */
}

function renderStars(rating, totalRatings) {
  let roundedRating = Math.round(rating);
  let remainder = rating - roundedRating;
  let result = "";
  for (let i = 0; i < 5; i++) {
    if (i < roundedRating) {
      result += '<small class="fa fa-star text-primary mr-1"></small>';
    } else if (i === roundedRating && remainder > 0) {
      result += '<small class="fa fa-star-half text-primary mr-1"></small>';
    } else {
      result += '<small class="fa fa-star mr-1"></small>';
    }
  }
  result += "<small>(" + totalRatings + ")</small>";
  return result;
}
