const totalSalesProduct = document.getElementById("totalSalesProduct");
const totalSalesIncome = document.getElementById("totalSalesIncome");
const totalSalesBill = document.getElementById("totalSalesBill");
const resentBillTable = document.getElementById("resentBillTable");
const topProductSales = document.getElementById("topProductSales");

function getToDayTotalProductSale() {}
function getThisMouthTotalProductSale() {}
function getThisYearTotalProductSale() {}

function getToDayTopProductSale() {}
function getThisMouthTopProductSale() {}
function getThisYearTopProductSale() {}

function getToDayToTalIncome() {}
function getThisMouthToTalIncome() {}
function getThisYearToTalIncome() {}

function getToDayTotalBill() {}
function getThisMouthTotalBill() {}
function getThisYearTotalBill() {}

let pageno = 1,
  sortby = "createdAt",
  pagezise = 0;
function updatePageno(page) {
  pageno = page;
  getResentBill();
}
function updateSort(sort) {
  sortby = sort;
  getResentBill();
}
function updatePageSize(pages) {
  pagezise = pages;
  getResentBill();
}
function getResentBill() {}
