let app_color = angular.module("customer", []);
app_color.controller("customer-ctrl", function ($scope, $http, $timeout) {
  $scope.customers = [];
  $scope.formUpdate = {};
  $scope.formInput = {};
  $scope.showAlert = false;
  $scope.showSuccessMessage = function (message) {
    $scope.alertMessage = message;
    $scope.showAlert = true;
    $timeout(function () {
      $scope.closeAlert();
    }, 5000);
  };
  $scope.closeAlert = function () {
    $scope.showAlert = false;
  };
  $scope.initialize = function () {
    $http
      .get("/test/counter/productDetailList")
      .then((resp) => {
        $scope.customers = resp.data;
        console.log(resp.data);
      });
  };
  $scope.initialize();
  $scope.edit = function (cate) {
    $scope.formUpdate = angular.copy(cate);
  };
  $scope.create = function () {
    let item = angular.copy($scope.formInput);
    $http
      .post(`/rest/customers`, item)
      .then((resp) => {
        $scope.showSuccessMessage("Create color successfully!");
        $scope.resetFormInput();
        $scope.initialize();
        $("#modalAdd").modal("hide");
      })
      .catch((error) => {
        console.log("Error", error);
      });
  };

  $scope.update = function () {
    let item = angular.copy($scope.formUpdate);
    $http
      .put(`/rest/customers/${item.id}`, item)
      .then((resp) => {
        $scope.showSuccessMessage("Update color successfully!");
        $scope.resetFormUpdate();
        $scope.initialize();
        $("#modalUpdate").modal("hide");
      })
      .catch((error) => {
        console.log("Error", error);
      });
  };

  $scope.delete = function (item) {
    $http
      .delete(`/rest/customers/${item.id}`)
      .then((resp) => {
        $scope.showSuccessMessage("Delete color successfully!");
        $scope.initialize();
      })
      .catch((error) => {
        console.log("Error", error);
      });
  };

  $scope.resetFormUpdate = function () {
    $scope.formUpdate = {};
    $scope.formUpdateColor.$setPristine();
    $scope.formUpdateColor.$setUntouched();
  };

  $scope.resetFormInput = function () {
    $scope.formInput = {};
    $scope.formAddColor.$setPristine();
    $scope.formAddColor.$setUntouched();
  };

  $scope.paper = {
    page: 0,
    size: 5,
    get items() {
      let start = this.page * this.size;
      return $scope.customers.slice(start, start + this.size);
    },
    get count() {
      return Math.ceil((1.0 * $scope.customers.length) / this.size);
    },
    first() {
      this.page = 0;
    },
    prev() {
      this.page--;
      if (this.page < 0) {
        this.last();
      }
    },
    next() {
      this.page++;
      if (this.page >= this.count) {
        this.first();
      }
    },
    last() {
      this.page = this.count - 1;
    },
  };
});
