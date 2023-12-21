const totalSalesProduct = document.getElementById("totalSalesProduct");
const totalSalesIncome = document.getElementById("totalSalesIncome");
const totalSalesBill = document.getElementById("totalSalesBill");
const resentBillTable = document.getElementById("resentBillTable");
const topProductSales = document.getElementById("topProductSales");
const navtabChar = document.getElementById("navtab-char");
const incomegrow = document.getElementById("incomegrow");
var chartype = 1;
var timetype = 1;
function formatAsVND(amount) {
  const formatter = new Intl.NumberFormat("vi-VN", {
    style: "currency",
    currency: "VND",
  });

  return formatter.format(amount);
}
const filtertotalSalesProduct = document.getElementById(
  "filterTotalSalesProduct"
);
const filtertotalSalesIncome = document.getElementById(
  "filterTotalSalesIncome"
);
const filtertotalSalesBill = document.getElementById("filterTotalSalesBill");
const filterresentBillTable = document.getElementById("filterResentBillTable");
const filtertopProductSales = document.getElementById("filterTopProductSales");
const fillterforchar = document.getElementById("fillterforchar");

// Today
async function getToDayTotalProductSale() {
  try {
    const response = await fetch("/admin/api/thongke/todaytotalproductsale");
    const data = await response.json();
    totalSalesProduct.innerText = data.toString();
    filtertotalSalesProduct.innerText = ` | Hôm nay`;
  } catch (error) {
    console.error("Error fetching today total product sale:", error);
    totalSalesProduct.innerText = 0;
  }
}
async function getToDayToTalIncome() {
  try {
    const response = await fetch("/admin/api/thongke/todaydoanhthu");
    const data = await response.json();
    totalSalesIncome.innerText = formatAsVND(data.toString());
    filtertotalSalesIncome.innerText = ` | Hôm nay`;
    getGropThanYesterday();
  } catch (error) {
    console.error("Error fetching today total income:", error);
    totalSalesIncome.innerText = 0;
  }
}
async function getGropThanYesterday() {
  const response = await fetch("/admin/api/thongke/getgrowthanyesterday");
  const data = await response.json(); // Parse the response body as JSON
  console.log(data);
  setIncomeGrow(data, "Hôm qua");
}
async function getGropThanLastMouth() {
  const response = await fetch("/admin/api/thongke/getgrowththanlastmonth");
  const data = await response.json(); // Parse the response body as JSON
  console.log(data);
  setIncomeGrow(data, "Tháng trước");
}

async function getGropThanLastYear() {
  const response = await fetch("/admin/api/thongke/getgrowththanlastyear");
  const data = await response.json(); // Parse the response body as JSON
  console.log(data);
  setIncomeGrow(data, "Năm trước");
}
function setIncomeGrow(data, text) {
  if (data == -1000) {
    incomegrow.classList.remove("text-success");
    incomegrow.classList.remove("text-danger");
    incomegrow.classList.add("text-secondary");
    incomegrow.innerText = `${text} không có doanh thu`;
    return;
  }
  if (data < 0) {
    incomegrow.classList.remove("text-success");
    incomegrow.classList.add("text-danger");
  } else if (data == 0) {
    incomegrow.classList.remove("text-success");
    incomegrow.classList.remove("text-danger");
    incomegrow.classList.add("text-secondary");
  } else {
    incomegrow.classList.remove("text-danger");
    incomegrow.classList.add("text-success");
  }
  incomegrow.innerText = `${data} % so với ${text}`;
}
async function getToDayTotalBill() {
  try {
    const response = await fetch("/admin/api/thongke/todaytotalbill");
    const data = await response.json();
    totalSalesBill.innerText = data.toString();
    filtertotalSalesBill.innerText = ` | Hôm nay`;
  } catch (error) {
    console.error("Error fetching today total bill:", error);
  }
}
async function getToDayTopProductSale() {
  try {
    const response = await fetch("/admin/api/thongke/todaytopproductsales");
    const data = await response.json();

    const tbody = document.getElementById("topProductSales");
    tbody.innerHTML = "";
    if (data.length === 0) {
      console.log("No data available.");
    }
    data.forEach((product) => {
      const row = document.createElement("tr");

      const imageCell = document.createElement("th");
      imageCell.setAttribute("scope", "row");
      if (
        product.productDetail?.idProduct?.imageProducts &&
        product.productDetail.idProduct.imageProducts.length > 0
      ) {
        const img = document.createElement("img");
        img.setAttribute(
          "src",
          product.productDetail.idProduct.imageProducts[0].img
        );
        img.setAttribute("alt", "");
        imageCell.appendChild(img);
      }
      row.appendChild(imageCell);

      const nameCell = document.createElement("td");
      const nameSpan = document.createElement("span");
      nameSpan.textContent = product.name;

      const patternSpan = document.createElement("span");
      patternSpan.textContent = " - " + product.productDetail?.idPattern?.name;

      const sizeSpan = document.createElement("span");
      sizeSpan.textContent = " - " + product.productDetail?.idSize?.name;

      nameCell.appendChild(nameSpan);
      nameCell.appendChild(patternSpan);
      nameCell.appendChild(sizeSpan);

      row.appendChild(nameCell);

      const priceCell = document.createElement("td");
      const formattedPrice = formatAsVND(product.price);
      priceCell.textContent = formattedPrice;

      row.appendChild(priceCell);

      const quantityCell = document.createElement("td");
      quantityCell.classList.add("fw-bold");
      quantityCell.textContent = product.totalQuantity;

      row.appendChild(quantityCell);

      const totalPriceCell = document.createElement("td");
      const formattedTotalPrice = formatAsVND(product.totalPrice);
      totalPriceCell.textContent = formattedTotalPrice;

      row.appendChild(totalPriceCell);

      tbody.appendChild(row);
    });
    filtertopProductSales.innerHTML = ` | Hôm nay`;
  } catch (error) {
    console.error("Error fetching today's top product sales:", error);
  }
}
// mouth
async function getThisMouthTotalProductSale() {
  try {
    const response = await fetch(
      "/admin/api/thongke/thismouthtotalproductsale"
    );
    const data = await response.json();
    totalSalesProduct.innerText = data.toString();
    filtertotalSalesProduct.innerText = ` | Tháng này`;
  } catch (error) {
    console.error("Error fetching today total product sale:", error);
    totalSalesProduct.innerText = 0;
  }
}
async function getThisMouthToTalIncome() {
  try {
    const response = await fetch("/admin/api/thongke/thismouthdoanhthu");
    const data = await response.json();
    totalSalesIncome.innerText = formatAsVND(data.toString());
    filtertotalSalesIncome.innerText = ` | Tháng này`;
    getGropThanLastMouth();
  } catch (error) {
    console.error("Error fetching this month total income:", error);
  }
}
async function getThisMouthTotalBill() {
  try {
    const response = await fetch("/admin/api/thongke/thismouthtotalbill");
    const data = await response.json();
    totalSalesBill.innerText = data.toString();
    filtertotalSalesBill.innerText = ` | Tháng này`;
  } catch (error) {
    console.error("Error fetching this month total bill:", error);
  }
}
async function getThisMouthTopProductSale() {
  try {
    const response = await fetch("/admin/api/thongke/thismouthtopproductsales");
    const data = await response.json();

    const tbody = document.getElementById("topProductSales"); // Assuming you have a tbody with id="topProductSales" in your HTML
    tbody.innerHTML = "";
    if (data.length === 0) {
      tbody.innerHTML = "";
    }
    data.forEach((product) => {
      const row = document.createElement("tr");

      const imageCell = document.createElement("th");
      imageCell.setAttribute("scope", "row");

      if (
        product.productDetail?.idProduct?.imageProducts &&
        product.productDetail.idProduct.imageProducts.length > 0
      ) {
        const img = document.createElement("img");
        img.setAttribute(
          "src",
          product.productDetail.idProduct.imageProducts[0].img
        );
        img.setAttribute("alt", "");
        imageCell.appendChild(img);
      }
      row.appendChild(imageCell);

      const nameCell = document.createElement("td");
      const nameSpan = document.createElement("span");
      nameSpan.textContent = product.name;

      const patternSpan = document.createElement("span");
      patternSpan.textContent = " - " + product.productDetail?.idPattern?.name;

      const sizeSpan = document.createElement("span");
      sizeSpan.textContent = " - " + product.productDetail?.idSize?.name;

      nameCell.appendChild(nameSpan);
      nameCell.appendChild(patternSpan);
      nameCell.appendChild(sizeSpan);

      row.appendChild(nameCell);

      const priceCell = document.createElement("td");
      const formattedPrice = formatAsVND(product.price);
      priceCell.textContent = formattedPrice;

      row.appendChild(priceCell);

      const quantityCell = document.createElement("td");
      quantityCell.classList.add("fw-bold");
      quantityCell.textContent = product.totalQuantity;

      row.appendChild(quantityCell);

      const totalPriceCell = document.createElement("td");
      const formattedTotalPrice = formatAsVND(product.totalPrice);
      totalPriceCell.textContent = formattedTotalPrice;

      row.appendChild(totalPriceCell);

      tbody.appendChild(row);
    });
  } catch (error) {
    console.error("Error fetching today's top product sales:", error);
  }
  filtertopProductSales.innerHTML = ` | Tháng nay`;
}
// This Year
async function getThisYearToTalIncome() {
  try {
    const response = await fetch("/admin/api/thongke/thisyeardoanhthu");
    const data = await response.json();
    totalSalesIncome.innerText = formatAsVND(data.toString());
    filtertotalSalesIncome.innerText = ` | Năm nay`;
    getGropThanLastYear();
  } catch (error) {
    console.error("Error fetching this year total income:", error);
  }
}
async function getThisYearTotalBill() {
  try {
    const response = await fetch("/admin/api/thongke/thisyeartotalbill");
    const data = await response.json();
    totalSalesBill.innerText = data.toString();
    filtertotalSalesBill.innerText = ` | Năm nay`;
  } catch (error) {
    console.error("Error fetching this year total bill:", error);
  }
}
async function getThisYearTopProductSale() {
  try {
    const response = await fetch("/admin/api/thongke/thisyeartopproductsales");
    const data = await response.json();

    const tbody = document.getElementById("topProductSales");
    tbody.innerHTML = "";
    if (data.length === 0) {
      console.log("No data available.");
    }
    data.forEach((product) => {
      const row = document.createElement("tr");

      const imageCell = document.createElement("th");
      imageCell.setAttribute("scope", "row");

      if (
        product.productDetail?.idProduct?.imageProducts &&
        product.productDetail.idProduct.imageProducts.length > 0
      ) {
        const img = document.createElement("img");
        img.setAttribute(
          "src",
          product.productDetail.idProduct.imageProducts[0].img
        );
        img.setAttribute("alt", "");
        imageCell.appendChild(img);
      }
      row.appendChild(imageCell);

      const nameCell = document.createElement("td");
      const nameSpan = document.createElement("span");
      nameSpan.textContent = product.name;

      const patternSpan = document.createElement("span");
      patternSpan.textContent = " - " + product.productDetail?.idPattern?.name;

      const sizeSpan = document.createElement("span");
      sizeSpan.textContent = " - " + product.productDetail?.idSize?.name;

      nameCell.appendChild(nameSpan);
      nameCell.appendChild(patternSpan);
      nameCell.appendChild(sizeSpan);

      row.appendChild(nameCell);

      const priceCell = document.createElement("td");
      const formattedPrice = formatAsVND(product.price);
      priceCell.textContent = formattedPrice;

      row.appendChild(priceCell);

      const quantityCell = document.createElement("td");
      quantityCell.classList.add("fw-bold");
      quantityCell.textContent = product.totalQuantity;

      row.appendChild(quantityCell);

      const totalPriceCell = document.createElement("td");
      const formattedTotalPrice = formatAsVND(product.totalPrice);
      totalPriceCell.textContent = formattedTotalPrice;

      row.appendChild(totalPriceCell);

      tbody.appendChild(row);
    });
  } catch (error) {
    console.error("Error fetching today's top product sales:", error);
  }
  filtertopProductSales.innerHTML = ` | Năm nay`;
}
async function getThisYearTotalProductSale() {
  try {
    const response = await fetch("/admin/api/thongke/thisyeartotalproductsale");
    const data = await response.json();
    totalSalesProduct.innerText = data.toString();
    filtertotalSalesProduct.innerText = ` | Năm nay`;
  } catch (error) {
    console.error("Error fetching today total product sale:", error);
    totalSalesProduct.innerText = 0;
  }
}
async function getResentBill() {
  try {
    const response = await fetch("/admin/api/thongke/recentbills");
    const data = await response.json();

    const tbody = document.getElementById("resentBillTable");
    tbody.innerHTML = ""; // Clear the existing content of tbody

    data.forEach((item) => {
      const row = document.createElement("tr");

      const codeCell = document.createElement("th");
      codeCell.setAttribute("scope", "row");
      const codeLink = document.createElement("a");
      codeLink.href = "#";
      codeLink.textContent = item.productName;
      codeCell.appendChild(codeLink);
      row.appendChild(codeCell);

      const employeeCell = document.createElement("td");
      employeeCell.textContent = item.employee?.name;
      row.appendChild(employeeCell);

      const codeTableCell = document.createElement("td");
      codeTableCell.textContent = item.code;
      row.appendChild(codeTableCell);

      const totalMoneyCell = document.createElement("td");
      totalMoneyCell.textContent = item.total_money;
      row.appendChild(totalMoneyCell);

      const statusCell = document.createElement("td");
      statusCell.textContent = item.status;
      row.appendChild(statusCell);

      tbody.appendChild(row);
    });
  } catch (error) {
    console.error("Error fetching today's top product sales:", error);
  }
}
function getStatusBadge(status) {
  if (status == 5) {
    const badge = document.createElement("span");
    badge.className = "badge bg-success";
    badge.textContent = "Thành Công";
    return badge;
  }
  if (status == 1) {
    const badge = document.createElement("span");
    badge.className = "badge text-bg-warning";
    badge.textContent = "Chờ xác nhận";
    return badge;
  }
  if (status == 2) {
    const badge = document.createElement("span");
    badge.className = "badge text-bg-secondary";
    badge.textContent = "Chờ giao hàng";
    return badge;
  }
  if (status == 3) {
    const badge = document.createElement("span");
    badge.className = "badge text-bg-info";
    badge.textContent = "Đang giao hàng";
    return badge;
  }
  if (status == 4) {
    const badge = document.createElement("span");
    badge.className = "badge badge text-bg-light";
    badge.textContent = "Đã giao hàng";
    return badge;
  }
  if (status == -1 || status == -2) {
    const badge = document.createElement("span");
    badge.className = "badge text-bg-danger";
    badge.textContent = "Đã Hủy";
    return badge;
  }

  const badge = document.createElement("span");
  badge.className = "badge text-bg-dark";
  badge.textContent = "Không xác định";
  return badge;
}
function createChart() {
  const ctx = document.getElementById("myChart");

  const chart = new Chart(ctx, {
    type: "bar",
    data: {
      labels: ["Red", "Blue", "Yellow", "Green", "Purple", "Orange"],
      datasets: [
        {
          label: "Doanh thu",
          data: [12, 19, 3, 5, 2, 3],
          borderWidth: 1,
        },
      ],
    },
    options: {
      scales: {
        y: {
          beginAtZero: true,
          max: calculateTopMargin(40, [12, 19, 3, 5, 2, 3]), // Set max value with top margin
        },
      },
      plugins: {
        legend: {
          display: true,
          position: "top",
        },
        background: {
          color: "rgba(0, 0, 0, 0.1)",
        },
      },
    },
  });
  return chart;
}
function calculateTopMargin(percentage, data) {
  const maxValue = Math.max(...data);
  const roundedValue = Math.ceil(maxValue / 1000000) * 1000000; // Round up to the nearest million
  return roundedValue + (roundedValue * percentage) / 100;
}
//chart
function selectChar(clickedButton, selectchartype) {
  chartype = selectchartype;
  var navItems = navtabChar.getElementsByClassName("char-cate");
  for (var i = 0; i < navItems.length; i++) {
    navItems[i].classList.remove("active");
  }
  clickedButton.classList.add("active");
  updateChar();
}
async function getcharbyurl(url) {
  try {
    const response = await axios.get("/admin/api/thongke/" + url);
    const newData = response.data;
    myChart.data.labels = Object.keys(newData);
    myChart.data.datasets[0].data = Object.values(newData);
    myChart.options.scales.y.max = calculateTopMargin(
      40,
      Object.values(newData)
    );
    myChart.update();
  } catch (error) {
    console.error("Error fetching data:", error);
  }
}
const myChart = createChart();

async function updateChar() {
  console.log("chartype " + chartype);
  console.log("timetype " + timetype);
  // chartype = 1 hours
  if (chartype == 1) {
    if (timetype == 1) {
      // Hôm nay
      getIncomeHourToday();
      return;
    }
    if (timetype == 2) {
      // Hôm qua
      getIncomeHourYesterday();
      return;
    }
    if (timetype == 3) {
      // Tuần này
      getIncomeHourThisWeek();
      return;
    }
    if (timetype == 4) {
      //Tuần trước
      getIncomeHourLastWeek();
      return;
    }
    if (timetype == 5) {
      // Tháng nay
      getIncomeHourThisMonth();
      return;
    }
    if (timetype == 6) {
      // Tháng trước
      getIncomeHourLastMonth;
      return;
    }
  }
  // chartype = 2 day of week
  if (chartype == 2) {
    if (timetype == 1) {
      // Hôm nay
      getIncomeToDay();
      return;
    }
    if (timetype == 2) {
      // Hôm qua
      getIncomeYeterday();
      return;
    }
    if (timetype == 3) {
      // Tuần này
      getIncomeDayThisWeek();
      return;
    }
    if (timetype == 4) {
      //Tuần trước
      getIncomeDayLastWeek();
      return;
    }
    if (timetype == 5) {
      // Tháng nay
      getIncomeDayThisMonth();
      return;
    }
    if (timetype == 6) {
      // Tháng trước
      getIncomeDayLastMonth();
      return;
    }
  }
  // chartype = 1 day of month
  if (chartype == 3) {
    if (timetype == 1) {
      // Hôm nay
      getIncomeToDay();
      return;
    }
    if (timetype == 2) {
      // Hôm qua
      getIncomeYeterday();
      return;
    }
    if (timetype == 3) {
      // Tuần này
      getIncomeThisweek();
      return;
    }
    if (timetype == 4) {
      //Tuần trước
      getIncomeLastWeek();
      return;
    }
    if (timetype == 5) {
      // Tháng nay
      getIncomeThisMouth();
      return;
    }
    if (timetype == 6) {
      // Tháng trước
      getIncomeLastMonth();
      return;
    }
  }
}
async function updatetimetype(selectbutton, selecttimetype) {
  timetype = selecttimetype;
  fillterforchar.innerText = "| " + selectbutton.textContent;
  updateChar();
}
function getIncomeThisMonth(year) {
  getcharbyurl(`income/thang?year=${year}`);
}
function getIncomeThisYear() {
  getcharbyurl("income/thisyear");
}
function getIncomeNam() {
  getcharbyurl("income/nam");
}
function getIncomeThisweek() {
  getcharbyurl("income/thisweek");
}
function getIncomeToDay() {
  getcharbyurl("income/today");
}
function getIncomeLastMonth() {
  getcharbyurl("income/lastmounth");
}
function getIncomeYeterday() {
  getcharbyurl("income/yesterday");
}
function getIncomeHourToday() {
  getcharbyurl("income/hour/today");
}
function getIncomeHourYesterday() {
  getcharbyurl("income/hour/yesterday");
}
function getIncomeHourThisWeek() {
  getcharbyurl("income/hour/thisweek");
}
function getIncomeHourThisMonth() {
  getcharbyurl("income/hour/thismounth");
}
function getIncomeThisMouth() {
  getcharbyurl("income/thismouth");
}
function getIncomeLastWeek() {
  getcharbyurl("income/lastweek");
}
function getIncomeDayThisWeek() {
  getcharbyurl("income/day/thisWeek");
}
function getIncomeDayLastWeek() {
  getcharbyurl("income/day/lastWeek");
}
function getIncomeDayThisMonth() {
  getcharbyurl("income/day/thisMonth");
}
function getIncomeDayLastMonth() {
  getcharbyurl("income/day/lastMonth");
}
function getIncomeHourLastWeek() {
  getcharbyurl("income/hour/lastweek");
}
function getIncomeHourLastMonth() {
  getcharbyurl("income/hour/lastmonth");
}
// call first
updateChar();
getToDayTotalProductSale();
getToDayToTalIncome();
getToDayTotalBill();
getToDayTopProductSale();
