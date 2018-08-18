(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["main"],{

/***/ "./src/$$_lazy_route_resource lazy recursive":
/*!**********************************************************!*\
  !*** ./src/$$_lazy_route_resource lazy namespace object ***!
  \**********************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

function webpackEmptyAsyncContext(req) {
	// Here Promise.resolve().then() is used instead of new Promise() to prevent
	// uncaught exception popping up in devtools
	return Promise.resolve().then(function() {
		var e = new Error('Cannot find module "' + req + '".');
		e.code = 'MODULE_NOT_FOUND';
		throw e;
	});
}
webpackEmptyAsyncContext.keys = function() { return []; };
webpackEmptyAsyncContext.resolve = webpackEmptyAsyncContext;
module.exports = webpackEmptyAsyncContext;
webpackEmptyAsyncContext.id = "./src/$$_lazy_route_resource lazy recursive";

/***/ }),

/***/ "./src/app/admin-http.service.ts":
/*!***************************************!*\
  !*** ./src/app/admin-http.service.ts ***!
  \***************************************/
/*! exports provided: AdminHttpService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AdminHttpService", function() { return AdminHttpService; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var AdminHttpService = /** @class */ (function () {
    function AdminHttpService(http) {
        this.http = http;
        // TODO:chang the url before build
        this.baseUrl = "adminRest";
    }
    AdminHttpService.prototype.adminLogIn = function (logIn) {
        return this.http.post(this.baseUrl + "/adminlogIn", logIn);
        // return this.response;
    };
    AdminHttpService.prototype.adminLogOut = function (logInId) {
        return this.http.post(this.baseUrl + "/adminLogout", logInId);
    };
    AdminHttpService.prototype.creatCompany = function (companyData) {
        return this.http.post(this.baseUrl + "/creatCompany", companyData);
    };
    AdminHttpService.prototype.deleteCompany = function (companyData) {
        return this.http.post(this.baseUrl + "/deleteCompany", companyData);
    };
    AdminHttpService.prototype.listAllCompanies = function (adminData) {
        return this.http.post(this.baseUrl + "/listAllCompany", adminData);
    };
    AdminHttpService.prototype.updateCompany = function (companyData) {
        return this.http.post(this.baseUrl + "/updateCompany", companyData);
    };
    //customers
    AdminHttpService.prototype.creatCustomer = function (requestData) {
        return this.http.post(this.baseUrl + "/creatCustomer", requestData);
    };
    AdminHttpService.prototype.updateCustomer = function (customerData) {
        return this.http.post(this.baseUrl + "/updateCustomer", customerData);
    };
    AdminHttpService.prototype.deleteCustomer = function (customerData) {
        return this.http.post(this.baseUrl + "/deleteCustomer", customerData);
    };
    AdminHttpService.prototype.listAllCustomers = function (adminData) {
        return this.http.post(this.baseUrl + "/listAllCustomers", adminData);
    };
    //incomes
    AdminHttpService.prototype.getCustomerIncome = function (spicificCustomerData) {
        return this.http.post(this.baseUrl + "/getCustomerIncome", spicificCustomerData);
    };
    AdminHttpService.prototype.getCompanyIncome = function (spicificCompanyData) {
        return this.http.post(this.baseUrl + "/getCompanyIncome", spicificCompanyData);
    };
    AdminHttpService.prototype.getAllIncome = function (adminData) {
        return this.http.post(this.baseUrl + "/getAllIncome", adminData);
    };
    AdminHttpService = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Injectable"])({
            providedIn: 'root'
        }),
        __metadata("design:paramtypes", [_angular_common_http__WEBPACK_IMPORTED_MODULE_1__["HttpClient"]])
    ], AdminHttpService);
    return AdminHttpService;
}());



/***/ }),

/***/ "./src/app/admin/admin.component.css":
/*!*******************************************!*\
  !*** ./src/app/admin/admin.component.css ***!
  \*******************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "h1{\r\n    text-align: center;\r\n}\r\n.logIn{\r\n    margin: 2em;\r\n    margin-right: 60%;\r\n}\r\n#companyId, #customerId{\r\n    margin: 8px ;\r\n}\r\n#companyId:hover, #customerId:hover{\r\nborder: 5px solid rgb(145, 138, 138);\r\n}"

/***/ }),

/***/ "./src/app/admin/admin.component.html":
/*!********************************************!*\
  !*** ./src/app/admin/admin.component.html ***!
  \********************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<h1>\n\n\n  Admin Service</h1>\n\n\n<!-- customer log in form start -->\n<div class=\"logIn \" [hidden]=\"logInSuccess\">\n  <form #adminLogIn=\"ngForm\" (ngSubmit)=\"onSubmit()\" novalidate>\n\n    <div>\n\n      <div class=\"form-group\">\n\n        <label for=\"inputUserName\">User Name</label>\n        <input type=\"text\" class=\"form-control\" [class.is-invalid]=\"name.invalid && name.touched\" name=\"Name\" required #name=\"ngModel\"\n          id=\"LogInUserName\" [(ngModel)]=\"logInModel.userName\">\n        <small class=\"text-danger\" [class.d-none]=\"name.valid || name.untouched \">user name is required</small>\n\n      </div>\n      <div class=\"form-group\">\n        <label for=\"inputUserName\">Password</label>\n        <input type=\"password\" class=\"form-control\" [class.is-invalid]=\"password.invalid && password.touched\" name=\"password\" required\n          #password=\"ngModel\" id=\"LogInPassword\" [(ngModel)]=\"logInModel.password\">\n        <small class=\"text-danger\" [class.d-none]=\"password.valid || password.untouched \">password is required</small>\n\n      </div>\n      <button type=\"submit\" class=\"btn btn-primary\" [disabled]=\"name.invalid || name.untouched || password.invalid || logInBtnClickedDisabled \">logIn</button>\n      <div>\n\n        <small class=\"text-danger\" [class.d-none]=\"!logInFailed \">log in failed wrong user name or password</small>\n      </div>\n\n    </div>\n  </form>\n</div>\n<div>\n  <button (click)=\"adminLogOut()\" [hidden]=!logInSuccess>log out</button>\n</div>\n<!-- admin log in form  end -->\n\n<div class=\"row\" [hidden]=\"!logInSuccess\">\n  <div class=\"col-3\">\n    <div class=\"nav flex-column nav-pills\" id=\"v-pills-tab\" role=\"tablist\" aria-orientation=\"vertical\">\n      <a class=\"nav-link\" id=\"v-pills-creat-tab\" data-toggle=\"pill\" href=\"#v-pills-creat\" role=\"tab\" aria-controls=\"v-pills-creat\"\n        aria-selected=\"true\">Creat Company</a>\n      <a class=\"nav-link\" id=\"v-pills-creat-customer-tab\" data-toggle=\"pill\" href=\"#v-pills-creat-customer\" role=\"tab\" aria-controls=\"v-pills-creat-customer\"\n        aria-selected=\"false\">Creat Customer</a>\n      <a class=\"nav-link\" id=\"v-pills-profile-tab\" (click)=\"listAllCompany()\" data-toggle=\"pill\" href=\"#v-pills-profile\" role=\"tab\"\n        aria-controls=\"v-pills-profile\" aria-selected=\"false\">Companies Updates </a>\n      <a class=\"nav-link\" id=\"v-pills-customers-list-tab\" (click)=\"listAllCustomers()\" data-toggle=\"pill\" href=\"#v-pills-customers-list\"\n        role=\"tab\" aria-controls=\"v-pills-customers-list\" aria-selected=\"false\">Customers Updates</a>\n      <a class=\"nav-link\" id=\"v-pills-income-list-tab\" (click)=\"listAllIncome()\" data-toggle=\"pill\" href=\"#v-pills-income-list\"\n        role=\"tab\" aria-controls=\"v-pills-income-list\" aria-selected=\"false\">Income Table</a>\n    </div>\n  </div>\n  <div class=\"col-9\">\n    <div class=\"tab-content\" id=\"v-pills-tabContent\">\n\n      <div class=\"tab-pane fade show active\" active id=\"v-pills-creat\" role=\"tabpanel\" aria-labelledby=\"v-pills-creat-tab\">\n        <div *ngIf=\"logInSuccess  ;else elseMassege\">\n\n          <form #adminCreatCompany=\"ngForm\" (ngSubmit)=\"onCreatCompany()\" novalidate>\n            <!--creat company-->\n            <div class=\"form-group\">\n              <label for=\"inputCompanyName\">Company Name</label>\n              <input type=\"text\" class=\"form-control\" [class.is-invalid]=\"companyName.invalid && companyName.touched\" name=\"companyName\"\n                #companyName=\"ngModel\" required id=\"companyName-creat\" [(ngModel)]=\"company.companyName\">\n              <small class=\"text-danger\" [class.d-none]=\"companyName.valid || companyName.untouched \">company name is required</small>\n\n            </div>\n            <div class=\"form-group\">\n              <label for=\"inputCompanyPassword\">Email</label>\n              <input type=\"Email\" class=\"form-control\" [class.is-invalid]=\"companyEmail.invalid && companyEmail.touched\" name=\"companyEmail\"\n                required email #companyEmail=\"ngModel\" id=\"companyEmail-creat\" [(ngModel)]=\"company.email\">\n              <small class=\"text-danger\" [class.d-none]=\"companyEmail.valid || companyEmail.untouched \">company email is required</small>\n\n            </div>\n            <div class=\"form-group\">\n              <label for=\"inputCompanyPassword\">Password</label>\n              <input type=\"password\" class=\"form-control\" [class.is-invalid]=\"companyPassword.invalid && companyPassword.touched\" name=\"companyPassword\"\n                required #companyPassword=\"ngModel\" id=\"companyPassword-creat\" [(ngModel)]=\"company.password\">\n              <small class=\"text-danger\" [class.d-none]=\"companyPassword.valid || companyPassword.untouched \">company password is required</small>\n            </div>\n\n            <button [disabled]=\"creatCompanyBtnClickedDisabled\" type=\"submit\" class=\"btn btn-primary\" [disabled]=\"companyName.invalid || companyName.untouched || companyEmail.invalid || companyEmail.untouched || companyPassword.invalid \">Creat</button>\n            <div>\n              <small class=\"text-danger\" [class.d-none]=\"!companyCreatedFailed \">name of the company is already used</small>\n              <small class=\"text-success\" [class.d-none]=\"!companyCreatedSuccess\">company created successfully</small>\n            </div>\n\n          </form>\n        </div>\n        <ng-template #elseMassege>\n          <div>log in to do this task</div>\n        </ng-template>\n      </div>\n      <div class=\"tab-pane fade\" id=\"v-pills-profile\" role=\"tabpanel\" aria-labelledby=\"v-pills-profile-tab\">\n        <!-- show list of companies start -->\n        <div *ngIf=\"logInSuccess && companiesListed ;else elseMassege\">\n          <div class=\"card text-center\" id=\"companyId\" *ngFor=\"let company of companies\">\n            <div class=\"card-header\">\n              Company Name : {{company.companyName}}\n            </div>\n            <div class=\"card-body\">\n              <h5 class=\"card-title\">{{company.email}}</h5>\n\n              <a (click)=\"saveCompanyForDelete(company)\" class=\"btn btn-primary\" data-toggle=\"modal\" data-target=\"#exampleModalCenter\">Delete</a>\n              <a (click)=\"saveCompanyForDelete(company)\" class=\"btn btn-primary\" data-toggle=\"collapse\" data-target=\"#updateCompanyModalCenter\"\n                aria-controls=\"#updateCompanyModalCenter\">Update</a>\n              <a (click)=\"listCompanyIncome(company)\" class=\"btn btn-primary\" data-toggle=\"collapse\" data-target=\"#companyIncomeModalCenter\"\n                aria-controls=\"#companyIncomeModalCenter\">Show Income</a>\n\n\n            </div>\n            <div class=\"card-footer text-muted\">\n\n            </div>\n          </div>\n\n        </div>\n        <!-- show list of companies end -->\n        <ng-template #elseMassege>\n          <div>log in to do this task</div>\n        </ng-template>\n      </div>\n\n\n      <!--ask if to delete company Modal start-->\n      <div class=\"modal fade\" id=\"exampleModalCenter\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"exampleModalCenterTitle\" aria-hidden=\"true\">\n        <div class=\"modal-dialog modal-dialog-centered\" role=\"document\">\n          <div class=\"modal-content\">\n            <div class=\"modal-header\">\n              <h5 class=\"modal-title\" id=\"exampleModalCenterTitle\">Delete company</h5>\n              <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">\n                <span aria-hidden=\"true\">&times;</span>\n              </button>\n            </div>\n            <div class=\"modal-body\">\n              do you want to delete this company ?\n\n            </div>\n            <div class=\"modal-footer\">\n              <button type=\"button\" class=\"btn btn-secondary\" data-dismiss=\"modal\">Close</button>\n              <button type=\"button\" (click)=deleteCompnay() class=\"btn btn-primary\" data-dismiss=\"modal\">Delete</button>\n            </div>\n          </div>\n        </div>\n      </div>\n      <!-- ask if to delete company modal end -->\n\n      <!-- Update company start -->\n      <div class=\"collapse\" id=\"updateCompanyModalCenter\">\n        <div class=\"card card-body\">\n          <form #adminUpdateCompany=\"ngForm\" (ngSubmit)=\"onUpdateCompany()\" novalidate>\n\n            <div class=\"form-group\">\n              <label for=\"updateCompanyName\">Company Name</label>\n              <input type=\"text\" disabled class=\"form-control\" [class.is-invalid]=\"companyNameUpdate.invalid && companyNameUpdate.touched\"\n                name=\"companyNameUpdate\" #companyNameUpdate=\"ngModel\" required id=\"companyName-update\" [(ngModel)]=\"updatedCompany.companyName\">\n              <small class=\"text-danger\" [class.d-none]=\"companyNameUpdate.valid || companyNameUpdate.untouched \">company name is required</small>\n            </div>\n            <div class=\"form-group\">\n              <label for=\"updateCompanyPassword\">Email</label>\n              <input type=\"Email\" class=\"form-control\" [class.is-invalid]=\"companyEmailUpdate.invalid && companyEmailUpdate.touched\" name=\"companyEmailUpdate\"\n                required email #companyEmailUpdate=\"ngModel\" id=\"companyEmail-update\" [(ngModel)]=\"updatedCompany.email\">\n              <small class=\"text-danger\" [class.d-none]=\"companyEmailUpdate.valid || companyEmailUpdate.untouched \">company email is required</small>\n            </div>\n            <div class=\"form-group\">\n              <label for=\"updateCompanyPassword\">Password</label>\n              <input type=\"password\" class=\"form-control\" [class.is-invalid]=\"companyPasswordUpdate.invalid && companyPasswordUpdate.touched\"\n                name=\"companyPasswordUpdate\" required #companyPasswordUpdate=\"ngModel\" id=\"companyPassword-update\" [(ngModel)]=\"updatedCompany.password\">\n              <small class=\"text-danger\" [class.d-none]=\"companyPasswordUpdate.valid || companyPasswordUpdate.untouched \">company password is required</small>\n            </div>\n\n            <button [disabled]=\"updateCompanyBtnClickedDisabled\" type=\"submit\" class=\"btn btn-primary\" [disabled]=\"  companyEmailUpdate.invalid ||  companyPasswordUpdate.invalid \"\n              aria-controls=\"#updateCompanyModalCenter\" data-toggle=\"collapse\" data-target=\"#updateCompanyModalCenter\" aria-controls=\"#updateCompanyModalCenter\">Update</button>\n\n          </form>\n\n        </div>\n      </div>\n      <!-- Update company end -->\n\n      <!-- company income list start -->\n      <div class=\"collapse\" id=\"companyIncomeModalCenter\">\n        <div class=\"card card-body\">\n          <div *ngIf=\"companyIncomeListed && logInSuccess\">\n            <div class=\"row\">\n              <div class=\"col-sm\">\n                actions Description\n              </div>\n              <div class=\"col-sm\">\n                amount\n              </div>\n\n            </div>\n            <div class=\"container\" *ngFor=\"let income of companyIncomeList\">\n              <div class=\"row\">\n                <div class=\"col-sm\">\n                  <div *ngIf=\"income.descrption === 'COMPANY_NEW_COUPON' else  elseMassege \">\n                    {{income.name}} creat new coupon\n                  </div>\n                  <ng-template #elseMassege>\n                    {{income.name}} update coupon\n\n                  </ng-template>\n                </div>\n                <div class=\"col-sm\">\n                  {{income.amount}}\n                </div>\n\n\n              </div>\n            </div>\n          </div>\n        </div>\n      </div>\n      <!-- company income list end -->\n\n\n      <!-- customers  -->\n\n      <div class=\"tab-pane fade\" id=\"v-pills-creat-customer\" role=\"tabpanel\" aria-labelledby=\"v-pills-creat-customer-tab\">\n        <!-- creat customer form start -->\n        <div *ngIf=\"logInSuccess  ;else elseMassege\">\n\n          <form #adminCreatCompany=\"ngForm\" (ngSubmit)=\"onCreatCustomer()\" novalidate>\n            <!--creat company-->\n            <div class=\"form-group\">\n              <label for=\"inputCustomerName\">Customer Name</label>\n              <input type=\"text\" class=\"form-control\" [class.is-invalid]=\"customerName.invalid && customerName.touched\" name=\"customerName\"\n                #customerName=\"ngModel\" required id=\"customerName-creat\" [(ngModel)]=\"customer.name\">\n              <small class=\"text-danger\" [class.d-none]=\"customerName.valid || customerName.untouched \">customer name is required</small>\n\n            </div>\n\n            <div class=\"form-group\">\n              <label for=\"inputCustomerPassword\">Password</label>\n              <input type=\"password\" class=\"form-control\" name=\"customerPassword\" required #customerPassword=\"ngModel\" id=\"customerPassword-creat\"\n                [(ngModel)]=\"customer.password\">\n              <small class=\"text-danger\" [class.d-none]=\"customerPassword.valid || customerPassword.untouched \">customer password is required</small>\n\n            </div>\n\n            <button type=\"submit\" [disabled]=\"creatCustomerBtnClickedDisabled\" class=\"btn btn-primary\" [disabled]=\"customerName.invalid || customerName.untouched  || customerPassword.invalid  \">Creat</button>\n            <div>\n              <small class=\"text-danger\" [class.d-none]=\"!customerCreatedFailed \">customer created failed name is already used</small>\n              <small class=\"text-success\" [class.d-none]=\"!customerCreatedSuccess \">customer created successfully</small>\n\n            </div>\n          </form>\n        </div>\n        <ng-template #elseMassege>\n          <div>log in to do this task</div>\n        </ng-template>\n        <!-- creat customer form end -->\n      </div>\n\n      <div class=\"tab-pane fade\" id=\"v-pills-customers-list\" role=\"tabpanel\" aria-labelledby=\"v-pills-customers-list-tab\">\n\n\n        <!-- show customer list Modal  start -->\n        <div *ngIf=\"logInSuccess && customersListed ;else elseMassege\">\n          <div class=\"card text-center\" id=\"customerId\" *ngFor=\"let customer of customersList\">\n            <div class=\"card-header\">\n              Customer Name : {{customer.name}}\n            </div>\n            <div class=\"card-body\">\n\n\n              <a (click)=\"saveCustomerForDeleteOrUpdate(customer)\" class=\"btn btn-primary\" data-toggle=\"modal\" data-target=\"#deletCustomerModalCenter\">Delete</a>\n              <a (click)=\"saveCustomerForDeleteOrUpdate(customer)\" class=\"btn btn-primary\" data-toggle=\"collapse\" data-target=\"#updateCustomerModalCenter\"\n                aria-controls=\"#updateCustomerModalCenter\">Update</a>\n              <a (click)=\"listCustomerIncome(customer)\" class=\"btn btn-primary\" data-toggle=\"collapse\" data-target=\"#customerIncomeModalCenter\"\n                aria-controls=\"#customerIncomeModalCenter\">Customer Income</a>\n\n\n            </div>\n            <div class=\"card-footer text-muted\">\n\n            </div>\n          </div>\n\n        </div>\n        <ng-template #elseMassege>\n          <div>log in to do this task</div>\n        </ng-template>\n        <!-- show customer list Modal  end -->\n\n\n        <!-- ask if the client want to delete customer start -->\n        <div class=\"modal fade\" id=\"deletCustomerModalCenter\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"deletCustomerModalCenterTitle\"\n          aria-hidden=\"true\">\n          <div class=\"modal-dialog modal-dialog-centered\" role=\"document\">\n            <div class=\"modal-content\">\n              <div class=\"modal-header\">\n                <h5 class=\"modal-title\" id=\"deletCustomerModalCenterTitle\">Delete Customer</h5>\n                <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">\n                  <span aria-hidden=\"true\">&times;</span>\n                </button>\n              </div>\n              <div class=\"modal-body\">\n                do you want to delete this Customer ?\n\n              </div>\n              <div class=\"modal-footer\">\n                <button type=\"button\" class=\"btn btn-secondary\" data-dismiss=\"modal\">Close</button>\n                <button type=\"button\" (click)=onDeleteCustomer() class=\"btn btn-primary\" data-dismiss=\"modal\">Delete</button>\n              </div>\n            </div>\n          </div>\n        </div>\n        <!-- ask if the client want to delete customer end -->\n\n        <!--  customr update form collapsed card start -->\n        <div class=\"collapse\" id=\"updateCustomerModalCenter\">\n          <div class=\"card card-body\">\n            <form #adminUpdateCustomer=\"ngForm\" (ngSubmit)=\"onUpdateCustomer()\" novalidate>\n\n              <!-- Update customer -->\n              <div class=\"form-group\">\n                <label for=\"updateCustomerName\">Customer Name</label>\n                <input type=\"text\" disabled class=\"form-control\" name=\"customerNameUpdate\" #CustomerNameUpdate=\"ngModel\" required id=\"customerName-update\"\n                  [(ngModel)]=\"updatedCustomer.name\">\n              </div>\n              <div class=\"form-group\">\n                <label for=\"updateCompanyPassword\">Password</label>\n                <input type=\"password\" class=\"form-control\" [class.is-invalid]=\"customerPasswordUpdate.invalid && customerPasswordUpdate.touched\"\n                  name=\"customerPasswordUpdate\" required #customerPasswordUpdate=\"ngModel\" id=\"customerPassword-update\" [(ngModel)]=\"updatedCustomer.password\">\n                <small class=\"text-danger\" [class.d-none]=\"customerPasswordUpdate.valid || customerPasswordUpdate.untouched \">customer password is required</small>\n\n              </div>\n\n              <button [disabled]=\"updateCustomerBtnClickedDisabled\" type=\"submit\" class=\"btn btn-primary\" [disabled]=\"  customerPasswordUpdate.invalid \"\n                data-toggle=\"collapse\" data-target=\"#updateCustomerModalCenter\" aria-controls=\"#updateCustomerModalCenter\">Update</button>\n\n            </form>\n          </div>\n        </div>\n        <!-- customr  update form collapsed card end -->\n\n\n\n        <!-- customr income list start-->\n        <div class=\"collapse\" id=\"customerIncomeModalCenter\">\n          <div class=\"card card-body\">\n            <div *ngIf=\"customerIncomeListed && logInSuccess \">\n              <div class=\"row\">\n                <div class=\"col-sm\">\n                  actions Description\n                </div>\n                <div class=\"col-sm\">\n                  amount\n                </div>\n\n              </div>\n              <div class=\"container\" *ngFor=\"let income of customerIncomeList\">\n                <div class=\"row\">\n                  <div class=\"col-sm\">\n                    <div *ngIf=\"income.descrption === 'CUSTOMER_PURCHASE'  \">\n                      {{income.name}} purchase coupon\n                    </div>\n\n                  </div>\n                  <div class=\"col-sm\">\n                    {{income.amount}}\n                  </div>\n\n\n                </div>\n              </div>\n            </div>\n          </div>\n        </div>\n        <!-- customr income list end-->\n\n\n      </div>\n\n\n\n\n      <!-- all clients income start-->\n      <div class=\"tab-pane fade\" id=\"v-pills-income-list\" role=\"tabpanel\" aria-labelledby=\"v-pills-income-list-tab\">\n        <div class=\"card card-body\">\n          <div *ngIf=\"allIncomelisted && logInSuccess ; else elseMassege\">\n            <div class=\"row\">\n              <div class=\"col-sm\">\n                actions Description\n              </div>\n              <div class=\"col-sm\">\n                amount\n              </div>\n\n            </div>\n            <div class=\"container\" *ngFor=\"let income of allIncomelist\">\n              <div class=\"row\">\n                <div class=\"col-sm\">\n                  <div *ngIf=\"income.descrption === 'COMPANY_NEW_COUPON'  \">\n                    <strong>{{income.name}}</strong>: creat new coupon\n                  </div>\n                  <div *ngIf=\"income.descrption === 'COMPANY_UPDATE_COUPON'  \">\n                    <strong>{{income.name}}</strong>: update coupon\n                  </div>\n\n                  <div *ngIf=\"income.descrption === 'CUSTOMER_PURCHASE'  \">\n                    <strong>{{income.name}}</strong>: purchase coupon\n                  </div>\n\n\n                </div>\n                <div class=\"col-sm\">\n                  {{income.amount}}\n                </div>\n\n\n              </div>\n            </div>\n          </div>\n\n          <ng-template #elseMassege>\n            log in to do this task\n\n          </ng-template>\n        </div>\n      </div>\n      <!-- all clients income end-->\n    </div>\n  </div>\n</div>"

/***/ }),

/***/ "./src/app/admin/admin.component.ts":
/*!******************************************!*\
  !*** ./src/app/admin/admin.component.ts ***!
  \******************************************/
/*! exports provided: AdminComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AdminComponent", function() { return AdminComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _log_in_data__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ../log-in-data */ "./src/app/log-in-data.ts");
/* harmony import */ var _admin_http_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../admin-http.service */ "./src/app/admin-http.service.ts");
/* harmony import */ var _company__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../company */ "./src/app/company.ts");
/* harmony import */ var _customer__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ../customer */ "./src/app/customer.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};





var AdminComponent = /** @class */ (function () {
    function AdminComponent(adminHttpServic) {
        this.adminHttpServic = adminHttpServic;
        // logIn variables
        this.logInModel = new _log_in_data__WEBPACK_IMPORTED_MODULE_1__["LogInData"](this.logInId, "", "");
        this.logInSuccess = false;
        this.logInResponseMassege = "";
        this.logInBtnClickedDisabled = false;
        this.logInFailed = false;
        // company variables
        this.company = new _company__WEBPACK_IMPORTED_MODULE_3__["Company"]("", "", "");
        this.timpcompany = {};
        this.updatedCompany = new _company__WEBPACK_IMPORTED_MODULE_3__["Company"]("", "", "");
        this.companiesListed = false;
        this.creatCompanyBtnClickedDisabled = false;
        this.updateCompanyBtnClickedDisabled = false;
        this.companyCreatedFailed = false;
        this.companyCreatedSuccess = false;
        // customer variables
        this.customer = new _customer__WEBPACK_IMPORTED_MODULE_4__["Customer"]("", "");
        this.customersListed = false;
        this.updatedCustomer = new _customer__WEBPACK_IMPORTED_MODULE_4__["Customer"]("", "");
        this.updateCustomerBtnClickedDisabled = false;
        this.creatCustomerBtnClickedDisabled = false;
        this.customerCreatedFailed = false;
        this.customerCreatedSuccess = false;
        // income variables
        this.companyIncomeList = {};
        this.companyIncomeListed = false;
        this.customerIncomeList = {};
        this.customerIncomeListed = false;
        this.allIncomelist = {};
        this.allIncomelisted = false;
        this.logInId = null;
    }
    AdminComponent.prototype.ngOnInit = function () {
    };
    // Login functions 
    // sends the log in data to the server
    AdminComponent.prototype.onSubmit = function () {
        var _this = this;
        this.logInBtnClickedDisabled = true;
        this.adminHttpServic.adminLogIn(this.logInModel)
            .subscribe(function (data) { return _this.logInResponseData(data); }, function (error) { return console.log("error", error); });
    };
    //receive log in response data from the server
    // if the log in Succeeded the admin work space well appear and the log in form well disappear
    // if the log in failed a massage well appear for the client explaining what happened
    AdminComponent.prototype.logInResponseData = function (data) {
        this.logInResponseMassege = data.response.logInResponseMassege;
        if (this.logInId === null || this.logInId !== 0) {
            this.logInId = data.response.id;
        }
        if (this.logInResponseMassege === "LOGINSUCCESS") {
            this.logInSuccess = true;
            this.logInFailed = false;
        }
        else {
            this.logInFailed = true;
            this.logInSuccess = false;
        }
        this.logInBtnClickedDisabled = false;
    };
    // sends the client authorization id to log out from the server
    AdminComponent.prototype.adminLogOut = function () {
        var _this = this;
        var requestData = {
            clientId: this.logInId
        };
        this.adminHttpServic.adminLogOut(requestData)
            .subscribe(function (data) { return _this.logOutResponseData(data); }, function (error) { return console.log(error); });
    };
    // receive the data from the server response 
    // hide the admin work space
    AdminComponent.prototype.logOutResponseData = function (data) {
        this.logInSuccess = false;
    };
    // companies function 
    /////////////////////////////////////////
    // send the company data to the server to be created
    AdminComponent.prototype.onCreatCompany = function () {
        var _this = this;
        this.creatCompanyBtnClickedDisabled = true;
        var requestData = {
            clientId: this.logInId,
            company: this.company
        };
        this.adminHttpServic.creatCompany(requestData)
            .subscribe(function (data) { return _this.onCreatCompanyData(data); }, function (error) { return console.log(error); });
    };
    // receives the server response 
    // if the coupon  created successfully a massage well appear explains that it success 
    // if the server faild to creat the company a massage well appear explains that it failed
    AdminComponent.prototype.onCreatCompanyData = function (data) {
        if (data.response === "COMPANYCREATED") {
            this.companyCreatedSuccess = true;
            this.companyCreatedFailed = false;
        }
        if (data.responseMessage === "COMPANYNAMEISUSED") {
            this.companyCreatedSuccess = false;
            this.companyCreatedFailed = true;
        }
        this.creatCompanyBtnClickedDisabled = false;
    };
    //sends the Authorization id to get list of all the companies
    AdminComponent.prototype.listAllCompany = function () {
        var _this = this;
        this.companiesListed = false;
        var requestData = {
            clientId: this.logInId
        };
        this.adminHttpServic.listAllCompanies(requestData)
            .subscribe(function (data) { return _this.listAllCompaniesTrue(data); }, function (error) { return console.log(error); });
    };
    // receive the response from the server
    // save the list of the companies to local variable
    // shows the companies list in the work space
    AdminComponent.prototype.listAllCompaniesTrue = function (data) {
        this.companies = data.response;
        this.companiesListed = true;
    };
    // sends the authorization id and the company data
    // from the temporary variable to the srver to delete it
    AdminComponent.prototype.deleteCompnay = function () {
        var _this = this;
        var requestData = {
            clientId: this.logInId,
            company: this.timpcompany
        };
        this.adminHttpServic.deleteCompany(requestData).subscribe(function (data) { return _this.deletCompanydata(data); }, function (error) { return console.log(error); });
    };
    // receives the response from the server and change the companies list 
    // in the work space
    AdminComponent.prototype.deletCompanydata = function (data) {
        this.listAllCompany();
    };
    // saves the company in a temporary variable before update of delete
    AdminComponent.prototype.saveCompanyForDelete = function (company) {
        this.timpcompany = company;
        this.updatedCompany = company;
    };
    // sends the company data from the temporary variable 
    // and the authorization id to the server to update the company
    AdminComponent.prototype.onUpdateCompany = function () {
        var _this = this;
        this.updateCompanyBtnClickedDisabled = true;
        var requestData = {
            clientId: this.logInId,
            company: this.updatedCompany
        };
        this.adminHttpServic.updateCompany(requestData).
            subscribe(function (data) { return _this.updateComapnyData(data); });
    };
    AdminComponent.prototype.updateComapnyData = function (data) {
        this.listAllCompany();
        this.updateCompanyBtnClickedDisabled = false;
    };
    // customers functions
    //sends the customer data to creat new customer on the server 
    AdminComponent.prototype.onCreatCustomer = function () {
        var _this = this;
        this.creatCustomerBtnClickedDisabled = true;
        var requestData = {
            clientId: this.logInId,
            customer: this.customer
        };
        this.adminHttpServic.creatCustomer(requestData)
            .subscribe(function (data) { return _this.creatCustomerData(data); });
    };
    // receives the server response 
    // if the customer  created successfully a massage well appear explains that it success 
    // if the server faild to creat the customer a massage well appear explains that it failed
    AdminComponent.prototype.creatCustomerData = function (data) {
        this.creatCustomerBtnClickedDisabled = false;
        if (data.response === "CUSTOMERCREATED") {
            this.customerCreatedFailed = false;
            this.customerCreatedSuccess = true;
        }
        if (data.responseMessage === "CUSTOMERNAMEISUSED") {
            this.customerCreatedFailed = true;
            this.customerCreatedSuccess = false;
        }
    };
    // sends the authorization id and the customer data
    // from the temporary variable to the srver to delete it
    AdminComponent.prototype.onDeleteCustomer = function () {
        var _this = this;
        var requestData = {
            clientId: this.logInId,
            customer: this.timpCustomer
        };
        this.adminHttpServic.deleteCustomer(requestData)
            .subscribe(function (data) { return _this.deleteCustomerData(data); });
    };
    // receives the response from the server and change the list 
    // of the customers in the work space
    AdminComponent.prototype.deleteCustomerData = function (data) {
        this.listAllCustomers();
    };
    // saves the customer data in a temporary variable before update of delete
    AdminComponent.prototype.saveCustomerForDeleteOrUpdate = function (customer) {
        this.timpCustomer = customer;
        this.updatedCustomer = customer;
    };
    // sends the customer data from the temporary variable 
    // and the authorization id to the server to update the customer
    AdminComponent.prototype.onUpdateCustomer = function () {
        var _this = this;
        this.updateCustomerBtnClickedDisabled = true;
        var requestData = {
            clientId: this.logInId,
            customer: this.updatedCustomer
        };
        this.adminHttpServic.updateCustomer(requestData)
            .subscribe(function (data) { return _this.updateCustomerData(data); });
    };
    // receives the response from the server
    //  update the customers list in the work space
    AdminComponent.prototype.updateCustomerData = function (data) {
        this.updateCustomerBtnClickedDisabled = false;
        this.listAllCustomers();
    };
    // sends the Authorization id to get list of all the customers 
    AdminComponent.prototype.listAllCustomers = function () {
        var _this = this;
        var requestData = {
            clientId: this.logInId
        };
        this.adminHttpServic.listAllCustomers(requestData)
            .subscribe(function (data) { return _this.listAllCustomersTrue(data); });
    };
    // receive the response from the server
    // save the list of the customers to local variable
    // shows the customers list in the work space
    AdminComponent.prototype.listAllCustomersTrue = function (data) {
        this.customersList = data.response;
        this.customersListed = true;
    };
    // income function
    // sends the authorization id and the company data to the server 
    // to get list of company income
    AdminComponent.prototype.listCompanyIncome = function (company) {
        var _this = this;
        var requestData = {
            clientId: this.logInId,
            company: company
        };
        this.adminHttpServic.getCompanyIncome(requestData)
            .subscribe(function (data) { return _this.listCompanyIncomeData(data); });
    };
    // receives the response from the data base 
    // saves list of the income in local variable 
    // show the list in the admin work space 
    AdminComponent.prototype.listCompanyIncomeData = function (data) {
        this.companyIncomeList = data.response;
        this.companyIncomeListed = true;
    };
    // sends the authorization id and the customer data to the server 
    // to get list of customer income
    AdminComponent.prototype.listCustomerIncome = function (customer) {
        var _this = this;
        var requestData = {
            clientId: this.logInId,
            customer: customer
        };
        this.adminHttpServic.getCustomerIncome(requestData)
            .subscribe(function (data) { return _this.listCustomerIncomeData(data); });
    };
    // receives the response from the data base 
    // saves list of the income in local variable 
    // show the list in the admin work space
    AdminComponent.prototype.listCustomerIncomeData = function (data) {
        this.customerIncomeList = data.response;
        this.customerIncomeListed = true;
    };
    // sends the authorization id to the server 
    // to get list of customers and companies income
    AdminComponent.prototype.listAllIncome = function () {
        var _this = this;
        var requestData = {
            clientId: this.logInId,
        };
        this.adminHttpServic.getAllIncome(requestData)
            .subscribe(function (data) { return _this.getAllIncomeData(data); });
    };
    // receives the response from the data base 
    // saves list of the income in local variable 
    // show the list in the admin work space
    AdminComponent.prototype.getAllIncomeData = function (data) {
        this.allIncomelist = data.response;
        this.allIncomelisted = true;
    };
    AdminComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-admin',
            template: __webpack_require__(/*! ./admin.component.html */ "./src/app/admin/admin.component.html"),
            styles: [__webpack_require__(/*! ./admin.component.css */ "./src/app/admin/admin.component.css")]
        }),
        __metadata("design:paramtypes", [_admin_http_service__WEBPACK_IMPORTED_MODULE_2__["AdminHttpService"]])
    ], AdminComponent);
    return AdminComponent;
}());



/***/ }),

/***/ "./src/app/app-routing.module.ts":
/*!***************************************!*\
  !*** ./src/app/app-routing.module.ts ***!
  \***************************************/
/*! exports provided: AppRoutingModule, routingComponents */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AppRoutingModule", function() { return AppRoutingModule; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "routingComponents", function() { return routingComponents; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _customer_customer_component__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./customer/customer.component */ "./src/app/customer/customer.component.ts");
/* harmony import */ var _company_company_component__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./company/company.component */ "./src/app/company/company.component.ts");
/* harmony import */ var _admin_admin_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./admin/admin.component */ "./src/app/admin/admin.component.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};





var routes = [
    { path: 'customerService', component: _customer_customer_component__WEBPACK_IMPORTED_MODULE_2__["CustomerComponent"] },
    { path: 'companyService', component: _company_company_component__WEBPACK_IMPORTED_MODULE_3__["CompanyComponent"] },
    { path: 'adminService', component: _admin_admin_component__WEBPACK_IMPORTED_MODULE_4__["AdminComponent"] },
];
var AppRoutingModule = /** @class */ (function () {
    function AppRoutingModule() {
    }
    AppRoutingModule = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["NgModule"])({
            imports: [_angular_router__WEBPACK_IMPORTED_MODULE_1__["RouterModule"].forRoot(routes)],
            exports: [_angular_router__WEBPACK_IMPORTED_MODULE_1__["RouterModule"]]
        })
    ], AppRoutingModule);
    return AppRoutingModule;
}());

var routingComponents = [_customer_customer_component__WEBPACK_IMPORTED_MODULE_2__["CustomerComponent"], _company_company_component__WEBPACK_IMPORTED_MODULE_3__["CompanyComponent"], _admin_admin_component__WEBPACK_IMPORTED_MODULE_4__["AdminComponent"]];


/***/ }),

/***/ "./src/app/app.component.css":
/*!***********************************!*\
  !*** ./src/app/app.component.css ***!
  \***********************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ""

/***/ }),

/***/ "./src/app/app.component.html":
/*!************************************!*\
  !*** ./src/app/app.component.html ***!
  \************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<!--The content below is only a placeholder and can be replaced.-->\n<div style=\"text-align:center\">\n  \n</div>\n<nav>\n    <div class=\"nav nav-tabs\" id=\"nav-tab\" role=\"tablist\">\n      <a class=\"nav-item nav-link active\" id=\"nav-home-tab\" data-toggle=\"tab\" href=\"#nav-home\" routerLink=\"/customerService\"  role=\"tab\" aria-controls=\"nav-home\" aria-selected=\"true\">customer service</a>\n      <a class=\"nav-item nav-link\" id=\"nav-profile-tab\" data-toggle=\"tab\" href=\"#nav-home\" routerLink=\"/companyService\"  role=\"tab\" aria-controls=\"nav-profile\" aria-selected=\"false\">company service</a>\n      <a class=\"nav-item nav-link\" id=\"nav-contact-tab\" data-toggle=\"tab\" href=\"#nav-home\" routerLink=\"/adminService\"  role=\"tab\" aria-controls=\"nav-contact\" aria-selected=\"false\">admin service</a>\n    </div>\n  </nav>\n  \n  \n\n\n    <router-outlet></router-outlet>\n    <!--  Routed niews go here -->\n  \n\n\n\n"

/***/ }),

/***/ "./src/app/app.component.ts":
/*!**********************************!*\
  !*** ./src/app/app.component.ts ***!
  \**********************************/
/*! exports provided: AppComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AppComponent", function() { return AppComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};

var AppComponent = /** @class */ (function () {
    function AppComponent() {
        this.title = 'app';
    }
    AppComponent.prototype.clientService = function () {
    };
    AppComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-root',
            template: __webpack_require__(/*! ./app.component.html */ "./src/app/app.component.html"),
            styles: [__webpack_require__(/*! ./app.component.css */ "./src/app/app.component.css")]
        })
    ], AppComponent);
    return AppComponent;
}());



/***/ }),

/***/ "./src/app/app.module.ts":
/*!*******************************!*\
  !*** ./src/app/app.module.ts ***!
  \*******************************/
/*! exports provided: AppModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AppModule", function() { return AppModule; });
/* harmony import */ var _angular_platform_browser__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/platform-browser */ "./node_modules/@angular/platform-browser/fesm5/platform-browser.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var _app_component__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./app.component */ "./src/app/app.component.ts");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var _app_routing_module__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ./app-routing.module */ "./src/app/app-routing.module.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};






var AppModule = /** @class */ (function () {
    function AppModule() {
    }
    AppModule = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            declarations: [
                _app_component__WEBPACK_IMPORTED_MODULE_3__["AppComponent"],
                _app_routing_module__WEBPACK_IMPORTED_MODULE_5__["routingComponents"],
            ],
            imports: [
                _angular_forms__WEBPACK_IMPORTED_MODULE_4__["FormsModule"],
                _angular_platform_browser__WEBPACK_IMPORTED_MODULE_0__["BrowserModule"],
                _app_routing_module__WEBPACK_IMPORTED_MODULE_5__["AppRoutingModule"],
                _angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpClientModule"],
            ],
            providers: [],
            bootstrap: [_app_component__WEBPACK_IMPORTED_MODULE_3__["AppComponent"]]
        })
    ], AppModule);
    return AppModule;
}());



/***/ }),

/***/ "./src/app/company-http.service.ts":
/*!*****************************************!*\
  !*** ./src/app/company-http.service.ts ***!
  \*****************************************/
/*! exports provided: CompanyHttpService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CompanyHttpService", function() { return CompanyHttpService; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var CompanyHttpService = /** @class */ (function () {
    function CompanyHttpService(companyHttp) {
        this.companyHttp = companyHttp;
        this.baseUrl = "companyRest";
    }
    // sends the log in data to the server to check if the log in success or not 
    // return (LOGINSUCCESS  OR ALREADYLOGGEDIN) with authorization id OR LOGINFAILED
    CompanyHttpService.prototype.companyLogIn = function (logIn) {
        return this.companyHttp.post(this.baseUrl + "/companylogIn", logIn);
    };
    //  sends authorization id to log out 
    CompanyHttpService.prototype.companyLogOut = function (logInIdData) {
        return this.companyHttp.post(this.baseUrl + "/companyLogOut", logInIdData);
    };
    //  sends coupon data with authorization id to creat coupon
    CompanyHttpService.prototype.creatCoupon = function (couponData) {
        return this.companyHttp.post(this.baseUrl + "/creatCoupon", couponData);
    };
    //  sends coupon data with authorization id to delete coupon
    CompanyHttpService.prototype.deleteCoupon = function (couponData) {
        return this.companyHttp.post(this.baseUrl + "/deleteCoupon", couponData);
    };
    //  sends coupon data with authorization id to update coupon
    CompanyHttpService.prototype.updateCoupon = function (couponData) {
        return this.companyHttp.post(this.baseUrl + "/updateCoupon", couponData);
    };
    //  sends the specificCouponData to the server to get list of coupons depends 
    // on the type or price or endDate
    CompanyHttpService.prototype.getSpecificCoupons = function (couponTypeOrPriceOrEndDateData) {
        return this.companyHttp.post(this.baseUrl + "/getSpecificCoupons", couponTypeOrPriceOrEndDateData);
    };
    //  sends the authorization id to get list of all company coupons
    CompanyHttpService.prototype.listAllCoupons = function (logInIdData) {
        return this.companyHttp.post(this.baseUrl + "/listAllCoupons", logInIdData);
    };
    //  sends the authorization id to get list of all company income
    CompanyHttpService.prototype.getCompanyIncome = function (couponData) {
        return this.companyHttp.post(this.baseUrl + "/getCompanyIncome", couponData);
    };
    CompanyHttpService = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Injectable"])({
            providedIn: 'root'
        }),
        __metadata("design:paramtypes", [_angular_common_http__WEBPACK_IMPORTED_MODULE_1__["HttpClient"]])
    ], CompanyHttpService);
    return CompanyHttpService;
}());



/***/ }),

/***/ "./src/app/company.ts":
/*!****************************!*\
  !*** ./src/app/company.ts ***!
  \****************************/
/*! exports provided: Company */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "Company", function() { return Company; });
var Company = /** @class */ (function () {
    function Company(companyName, email, password) {
        this.companyName = companyName;
        this.email = email;
        this.password = password;
    }
    return Company;
}());



/***/ }),

/***/ "./src/app/company/company.component.css":
/*!***********************************************!*\
  !*** ./src/app/company/company.component.css ***!
  \***********************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "h1{\r\n    text-align: center;\r\n}\r\n.logIn{\r\n    margin: 2em;\r\n    margin-right: 60%;\r\n}"

/***/ }),

/***/ "./src/app/company/company.component.html":
/*!************************************************!*\
  !*** ./src/app/company/company.component.html ***!
  \************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<h1>Company Service</h1>\n\n\n<div class=\"logIn \" [hidden]=\"logInSuccess\">\n  <form #adminLogIn=\"ngForm\" (ngSubmit)=\"onSubmit()\" novalidate>\n\n    <div>\n      <div class=\"form-group\">\n\n        <label for=\"inputUserName\">User Name</label>\n        <input type=\"text\" class=\"form-control\" [class.is-invalid]=\"name.invalid && name.touched\" name=\"Name\" required #name=\"ngModel\"\n          id=\"LogInUserName\" [(ngModel)]=\"logInModel.userName\">\n        <small class=\"text-danger\" [class.d-none]=\"name.valid || name.untouched \">user name is required</small>\n\n      </div>\n      <div class=\"form-group\">\n        <label for=\"inputUserName\">Password</label>\n        <input type=\"password\" class=\"form-control\" [class.is-invalid]=\"password.invalid && password.touched\" name=\"password\" required\n          #password=\"ngModel\" id=\"LogInPassword\" [(ngModel)]=\"logInModel.password\">\n        <small class=\"text-danger\" [class.d-none]=\"password.valid || password.untouched \">password is required</small>\n        <div>\n          <small class=\"text-danger\" [class.d-none]=\"!logInFailed \">log in failed wrong user name or password</small>\n\n        </div>\n      </div>\n      <button type=\"submit\" class=\"btn btn-primary\" [disabled]=\"name.invalid || name.untouched || password.invalid || logInBtnClickedDisabled \">logIn</button>\n    </div>\n  </form>\n</div>\n<div>\n  <button (click)=\"companyLogOut()\" [hidden]=!logInSuccess>log out</button>\n</div>\n\n\n\n<!-- customer main content  start -->\n<div class=\"row\" [hidden]=!logInSuccess>\n  <!-- links for the content -->\n  <div class=\"col-3\">\n    <div class=\"nav flex-column nav-pills\" id=\"v-pills-tab\" role=\"tablist\" aria-orientation=\"vertical\">\n      <a class=\"nav-link\" id=\"v-pills-creat-coupon-tab\" data-toggle=\"pill\" href=\"#v-pills-creat-coupon\" role=\"tab\" aria-controls=\"v-pills-creat-coupon\"\n        aria-selected=\"true\">Creat Coupon</a>\n      <a class=\"nav-link\" id=\"v-pills-get-coupon-tab\" (click)=\"listAllCoupons(); \" data-toggle=\"pill\" href=\"#v-pills-get-coupon\"\n        role=\"tab\" aria-controls=\"v-pills-get-coupon\" aria-selected=\"false\">coupons </a>\n      <a class=\"nav-link\" id=\"v-pills-get-coupon-by-type-tab\" (click)=\"activateSearchByType()\" data-toggle=\"pill\" href=\"#v-pills-get-coupon-by-type\"\n        role=\"tab\" aria-controls=\"v-pills-get-coupon-by-type\" aria-selected=\"false\">coupons by type</a>\n      <a class=\"nav-link\" id=\"v-pills-get-coupon-by-price-tab\" (click)=\"activateSearchByPrice()\" data-toggle=\"pill\" href=\"#v-pills-get-coupon-by-price\"\n        role=\"tab\" aria-controls=\"v-pills-get-coupon-by-price\" aria-selected=\"false\">coupons by price</a>\n      <a class=\"nav-link\" id=\"v-pills-get-coupon-by-endDate-tab\" (click)=\"activateSearchByEndDate()\" data-toggle=\"pill\" href=\"#v-pills-get-coupon-by-endDate\"\n        role=\"tab\" aria-controls=\"v-pills-get-coupon-by-endDate\" aria-selected=\"false\">coupons by End Date</a>\n      <a class=\"nav-link\" id=\"v-pills-income-list-tab\" (click)=\"listAllCompanyIncome()\" data-toggle=\"pill\" href=\"#v-pills-list-income\"\n        role=\"tab\" aria-controls=\"v-pills-list-income\" aria-selected=\"false\">Income Table</a>\n    </div>\n  </div>\n  <!-- company work space -->\n  <div class=\"col-9\">\n    <div class=\"tab-content\" id=\"v-pills-tabContent\">\n\n\n\n      <div class=\"tab-pane fade show active\" active id=\"v-pills-creat-coupon\" role=\"tabpanel\" aria-labelledby=\"v-pills-creat-coupon-tab\">\n        <div *ngIf=\"logInSuccess\">\n\n          <form #companyCreatCoupon=\"ngForm\" (ngSubmit)=\"onCreatCoupon()\" novalidate>\n            <!--creat coupon start-->\n            <div class=\"form-group\">\n              <label for=\"inputCouponTitle\">Title</label>\n              <input type=\"text\" class=\"form-control\" [class.is-invalid]=\"couponTitle.invalid && couponTitle.touched \" name=\"couponTitle\"\n                #couponTitle=\"ngModel\" required id=\"couponTitle-creat\" [(ngModel)]=\"couponModel.title\">\n              <small class=\"text-danger\" [class.d-none]=\"couponTitle.valid || couponTitle.untouched \">title is required</small>\n            </div>\n            <div class=\"form-group\">\n              <label for=\"inputCouponStartDate\">startDate</label>\n              <input type=\"date\" class=\"form-control\" [class.is-invalid]=\"couponStartDate.invalid && couponStartDate.touched \" name=\"couponStartDate\"\n                #couponStartDate=\"ngModel\" required id=\"couponStartDate-creat\" [(ngModel)]=\"couponModel.startDate\">\n              <small class=\"text-danger\" [class.d-none]=\"couponStartDate.valid || couponStartDate.untouched \">startDate is required</small>\n            </div>\n            <div class=\"form-group\">\n              <label for=\"inputCouponEndDate\">endDate</label>\n              <input type=\"date\" class=\"form-control\" [class.is-invalid]=\"couponEndDate.invalid && couponEndDate.touched  \" name=\"'couponEndDate'\"\n                #couponEndDate=\"ngModel\" required id=\"couponEndDate-creat\" [(ngModel)]=\"couponModel.endDate\">\n              <small class=\"text-danger\" [class.d-none]=\"couponEndDate.valid || couponEndDate.untouched \">endDate is required</small>\n            </div>\n\n            <div class=\"form-group\">\n              <label for=\"inputCouponAmount\">Amount</label>\n              <input type=\"number\" class=\"form-control\" [class.is-invalid]=\"couponAmount.invalid && couponAmount.touched \" name=\"couponAmount\"\n                required #couponAmount=\"ngModel\" id=\"couponAmount-creat\" [(ngModel)]=\"couponModel.amount\">\n              <small class=\"text-danger\" [class.d-none]=\"couponAmount.valid || couponAmount.untouched \">Amount is required</small>\n\n            </div>\n            <div class=\"form-group\">\n              <label for=\"inputCouponPrice\">Price</label>\n              <input type=\"number\" class=\"form-control\" [class.is-invalid]=\"couponPrice.invalid && couponPrice.touched \" name=\"couponPrice\"\n                required #couponPrice=\"ngModel\" id=\"couponPrice-creat\" [(ngModel)]=\"couponModel.price\">\n              <small class=\"text-danger\" [class.d-none]=\"couponPrice.valid || couponPrice.untouched \">Amount is required</small>\n\n            </div>\n\n            <div class=\"form-group\">\n              <label for=\"inputCouponType\">Coupon type</label>\n              <select class=\"custom-select\" name=\"couponType\" [class.is-invalid]=\"couponType.invalid && couponType.touched\" required #couponType=\"ngModel\"\n                id=\"couponType-creat\" [(ngModel)]=\"couponModel.couponType\">\n\n                <option value=\"RESTURANS\" selected>RESTURANS</option>\n                <option value=\"ELECTRICITY\">ELECTRICITY</option>\n                <option value=\"FOOD\">FOOD</option>\n                <option value=\"HEALTH\">HEALTH</option>\n                <option value=\"SPORTS\">SPORTS</option>\n                <option value=\"CAMPING\">CAMPING</option>\n                <option value=\"TRAVELLING\">TRAVELLING</option>\n              </select>\n              <small class=\"text-danger\" [class.d-none]=\"couponType.valid || couponType.untouched \">couponType is required</small>\n\n            </div>\n\n            <div class=\"form-group\">\n              <label for=\"inputCouponMassege\">Massege </label>\n              <input type=\"text\" class=\"form-control\" [class.is-invalid]=\"couponMassege.invalid && couponMassege.touched\" name=\"couponMassege\"\n                required #couponMassege=\"ngModel\" id=\"couponMassege-creat\" [(ngModel)]=\"couponModel.massage\">\n              <small class=\"text-danger\" [class.d-none]=\"couponMassege.valid || couponMassege.untouched \">massege is required</small>\n\n            </div>\n            <div class=\"form-group\">\n              <label for=\"inputCouponImage\">Image </label>\n              <input type=\"text\" class=\"form-control\" [class.is-invalid]=\"couponImage.invalid && couponImage.touched\" name=\"couponImage\"\n                required #couponImage=\"ngModel\" id=\"couponImage-creat\" [(ngModel)]=\"couponModel.image\">\n              <small class=\"text-danger\" [class.d-none]=\"couponImage.valid || couponImage.untouched \">image is required</small>\n            </div>\n\n            <button [disabled]=\"creatCouponBtnClickedDisabled\" type=\"submit\" class=\"btn btn-primary\" [disabled]=\"couponTitle.invalid || couponTitle.untouched || couponStartDate.invalid || couponStartDate.untouched ||\n                        couponEndDate.invalid || couponEndDate.untouched ||couponAmount.invalid || couponAmount.untouched ||couponType.invalid || couponType.untouched ||couponMassege.invalid || couponMassege.untouched ||couponImage.invalid \">Creat</button>\n            <!--creat coupon end-->\n            <div>\n              <small class=\"text-success\" [class.d-none]=\"!couponCreatedSuccessfully\">coupon created successfully</small>\n              <small class=\"text-danger\" [class.d-none]=\"!couponCreatedFailed\">coupon created failed title may be already used</small>\n\n            </div>\n          </form>\n        </div>\n\n\n\n      </div>\n\n\n\n      <div class=\"tab-pane fade show active\" active id=\"v-pills-get-coupon\" role=\"tabpanel\" aria-labelledby=\"v-pills-get-coupon-tab\">\n\n\n\n        <!-- list company coupons cards start -->\n        <div *ngIf=\"logInSuccess && couponsListed \">\n          <div class=\"card text-center\" id=\"couponId\" *ngFor=\"let coupon of couponsList\">\n            <div class=\"card-header\">\n              Coupon title : {{coupon.title}}\n            </div>\n            <div class=\"card-body\">\n              <div class=\"row\">\n                <div class=\"col-4\">\n                  Coupon Start date : {{coupon.startDate}}\n                </div>\n                <div class=\"col-4\">\n                  Coupon End Date : {{coupon.endDate}}\n                </div>\n                <div class=\"col-4\">\n                  Coupon Price : {{coupon.price}}\n                </div>\n\n              </div>\n              <div class=\"row\">\n                <div class=\"col-4\">\n                  Coupon Type : {{coupon.couponType}}\n                </div>\n                <div class=\"col-4\">\n                  Coupon Amount : {{coupon.amount}}\n                </div>\n                <div class=\"col-12\">\n                  Massege : {{coupon.massage}}\n                </div>\n\n\n\n              </div>\n\n              <a (click)=\"saveCouponForDeleteOrUpdate(coupon)\" class=\"btn btn-primary\" data-toggle=\"modal\" data-target=\"#deletCouponModalCenter\">Delete</a>\n              <a (click)=\"saveCouponForDeleteOrUpdate(coupon)\" class=\"btn btn-primary\" data-toggle=\"collapse\" data-target=\"#updateCouponModalCenter\"\n                aria-controls=\"#updateCouponModalCenter\">Update</a>\n\n\n            </div>\n            <div class=\"card-footer text-muted\">\n\n            </div>\n          </div>\n\n        </div>\n\n        <!--list company coupons cards end -->\n\n        <!--ask if to delet the coupon Modal start -->\n        <div class=\"modal fade\" id=\"deletCouponModalCenter\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"deletCouponModalCenterTitle\"\n          aria-hidden=\"true\">\n          <div class=\"modal-dialog modal-dialog-centered\" role=\"document\">\n            <div class=\"modal-content\">\n              <div class=\"modal-header\">\n                <h5 class=\"modal-title\" id=\"deletCouponModalCenterTitle\">Delete Coupon</h5>\n                <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">\n                  <span aria-hidden=\"true\">&times;</span>\n                </button>\n              </div>\n              <div class=\"modal-body\">\n                do you want to delete this Coupon ?\n\n              </div>\n              <div class=\"modal-footer\">\n                <button type=\"button\" class=\"btn btn-secondary\" data-dismiss=\"modal\">Close</button>\n                <button type=\"button\" (click)=onDeleteCoupon() class=\"btn btn-primary\" data-dismiss=\"modal\">Delete</button>\n              </div>\n            </div>\n          </div>\n        </div>\n        <!--ask if to delet the coupon Modal end -->\n\n\n        <!-- update coupon collap start -->\n        <div class=\"collapse\" id=\"updateCouponModalCenter\">\n          <div class=\"card card-body\">\n            <form #companyCreatCoupon=\"ngForm\" (ngSubmit)=\"onUpdateCoupon()\" novalidate>\n              <!--update company-->\n              <div class=\"form-group\">\n                <label for=\"inputCouponTitle\">Title</label>\n                <input type=\"text\" class=\"form-control\" disabled name=\"couponTitle\" [class.is-invalid]=\"couponTitle.invalid && couponTitle.touched \"\n                  #couponTitle=\"ngModel\" required id=\"couponTitle-update\" [(ngModel)]=\"updatedCoupon.title\">\n                <small class=\"text-danger\" [class.d-none]=\"couponTitle.valid || couponTitle.untouched \">title is required</small>\n\n              </div>\n              <div class=\"form-group\">\n                <label for=\"inputCouponStartDate\">startDate</label>\n                <input type=\"date\" class=\"form-control\" [class.is-invalid]=\"couponStartDate.invalid && couponStartDate.touched \" name=\"couponStartDate\"\n                  #couponStartDate=\"ngModel\" required id=\"couponStartDate-update\" [(ngModel)]=\"updatedCoupon.startDate\">\n                <small class=\"text-danger\" [class.d-none]=\"couponStartDate.valid || couponStartDate.untouched \">coupon startDate is required</small>\n              </div>\n              <div class=\"form-group\">\n                <label for=\"inputCouponEndDate\">endDate</label>\n                <input type=\"date\" class=\"form-control\" [class.is-invalid]=\"couponEndDate.invalid && couponEndDate.touched \" name=\"'couponEndDate'\"\n                  #couponEndDate=\"ngModel\" required id=\"couponEndDate-update\" [(ngModel)]=\"updatedCoupon.endDate\">\n                <small class=\"text-danger\" [class.d-none]=\"couponEndDate.valid || couponEndDate.untouched \">coupon endDate is required</small>\n              </div>\n\n              <div class=\"form-group\">\n                <label for=\"inputCouponAmount\">Amount</label>\n                <input type=\"number\" class=\"form-control\" [class.is-invalid]=\"couponAmount.invalid && couponAmount.touched \" name=\"couponAmount\"\n                  required #couponAmount=\"ngModel\" id=\"couponAmount-update\" [(ngModel)]=\"updatedCoupon.amount\">\n                <small class=\"text-danger\" [class.d-none]=\"couponAmount.valid || couponAmount.untouched \">coupon amount is required</small>\n              </div>\n              <div class=\"form-group\">\n                <label for=\"inputCouponPrice\">Price</label>\n                <input type=\"number\" class=\"form-control\" [class.is-invalid]=\"couponPrice.invalid && couponPrice.touched \" name=\"couponPrice\"\n                  required #couponPrice=\"ngModel\" id=\"couponPrice-update\" [(ngModel)]=\"updatedCoupon.price\">\n                <small class=\"text-danger\" [class.d-none]=\"couponPrice.valid || couponPrice.untouched \">coupon Price is required</small>\n              </div>\n\n              <div class=\"form-group\">\n                <label for=\"inputCouponType\">Coupon type</label>\n                <select class=\"custom-select\" [class.is-invalid]=\"couponType.invalid && couponType.touched \" name=\"couponType\" required #couponType=\"ngModel\"\n                  id=\"couponType-update\" [(ngModel)]=\"updatedCoupon.couponType\">\n\n                  <option value=\"RESTURANS\" selected>RESTURANS</option>\n                  <option value=\"ELECTRICITY\">ELECTRICITY</option>\n                  <option value=\"FOOD\">FOOD</option>\n                  <option value=\"HEALTH\">HEALTH</option>\n                  <option value=\"SPORTS\">SPORTS</option>\n                  <option value=\"CAMPING\">CAMPING</option>\n                  <option value=\"TRAVELLING\">TRAVELLING</option>\n                </select>\n                <small class=\"text-danger\" [class.d-none]=\"couponType.valid || couponType.untouched \">coupon type is required</small>\n              </div>\n\n              <div class=\"form-group\">\n                <label for=\"inputCouponMassege\">Massege </label>\n                <input type=\"text\" class=\"form-control\" [class.is-invalid]=\"couponMassege.invalid && couponMassege.touched \" name=\"couponMassege\"\n                  required #couponMassege=\"ngModel\" id=\"couponMassege-update\" [(ngModel)]=\"updatedCoupon.massage\">\n                <small class=\"text-danger\" [class.d-none]=\"couponMassege.valid || couponMassege.untouched \">coupon massage is required</small>\n\n              </div>\n              <div class=\"form-group\">\n                <label for=\"inputCouponImage\">Image </label>\n                <input type=\"text\" class=\"form-control\" [class.is-invalid]=\"couponImage.invalid && couponImage.touched \" name=\"couponImage\"\n                  required #couponImage=\"ngModel\" id=\"couponImage-update\" [(ngModel)]=\"updatedCoupon.image\">\n                <small class=\"text-danger\" [class.d-none]=\"couponImage.valid || couponImage.untouched \">coupon image is required</small>\n\n              </div>\n\n              <button [disabled]=\"updataCouponBtnDisabled\" type=\"submit\" class=\"btn btn-primary\" [disabled]=\"couponTitle.invalid  || couponStartDate.invalid  ||\n          couponEndDate.invalid ||couponAmount.invalid  ||couponType.invalid  ||couponMassege.invalid  ||couponImage.invalid \"\n                data-toggle=\"collapse\" data-target=\"#updateCouponModalCenter\" aria-controls=\"#updateCouponModalCenter\">Update</button>\n             \n            </form>\n          </div>\n        </div>\n        <!-- update coupon collap end -->\n      </div>\n\n      <!-- ////////////////////////////////////////////////////////////////////////////////////////////// -->\n      <!-- ////////////////////////////////////////////////////////////////////////////////////////////////////             -->\n\n\n\n\n\n      <!--search coupon by type start -->\n      <div class=\"tab-pane fade \" active id=\"v-pills-get-coupon-by-type\" role=\"tabpanel\" aria-labelledby=\"v-pills-get-coupon-by-type-tab\">\n        <div *ngIf=\"logInSuccess && activateGetCouponByTypeTab \">\n          <!-- search coupon by type input -->\n          <form #searchByCouponTypeForm=\"ngForm\" (ngSubmit)=\"listSpecificCouponsByType()\" novalidate>\n            <div class=\"form-row align-items-center\">\n              Chose coupon type :\n              <div class=\"col-auto my-1\">\n\n                <select class=\"custom-select mr-sm-2\" [class.is-invalid]=\"couponType.invalid && couponType.touched \" name=\"couponType\" required\n                  #couponType=\"ngModel\" id=\"couponType-search\" [(ngModel)]=\"serchDataForCoupons.couponType\">\n\n                  <option value=\"RESTURANS\" selected>RESTURANS</option>\n                  <option value=\"ELECTRICITY\">ELECTRICITY</option>\n                  <option value=\"FOOD\">FOOD</option>\n                  <option value=\"HEALTH\">HEALTH</option>\n                  <option value=\"SPORTS\">SPORTS</option>\n                  <option value=\"CAMPING\">CAMPING</option>\n                  <option value=\"TRAVELLING\">TRAVELLING</option>\n                </select>\n              </div>\n              <small class=\"text-danger\" [class.d-none]=\"couponType.valid || couponType.untouched \">coupon type is required</small>\n\n              <div class=\"col-auto my-1\">\n                <button type=\"submit\" [disabled]=\"couponType.invalid\" class=\"btn btn-primary\">Search</button>\n              </div>\n            </div>\n          </form>\n        </div>\n\n\n        <!--show coupon by type cards start -->\n        <div *ngIf=\"logInSuccess && specificByTypeCouponsListed \">\n          <div class=\"card text-center\" id=\"couponIdByType\" *ngFor=\"let coupon of specificByTypeCouponsList\">\n            <div class=\"card-header\">\n              Coupon title : {{coupon.title}}\n            </div>\n            <div class=\"card-body\">\n              <div class=\"row\">\n                <div class=\"col-4\">\n                  Coupon Start date : {{coupon.startDate}}\n                </div>\n                <div class=\"col-4\">\n                  Coupon End Date : {{coupon.endDate}}\n                </div>\n                <div class=\"col-4\">\n                  Coupon Price : {{coupon.price}}\n                </div>\n\n              </div>\n              <div class=\"row\">\n                <div class=\"col-4\">\n                  Coupon Type : {{coupon.couponType}}\n                </div>\n                <div class=\"col-4\">\n                  Coupon Amount : {{coupon.amount}}\n                </div>\n                <div class=\"col-12\">\n                  Massege : {{coupon.massage}}\n                </div>\n\n\n\n              </div>\n\n              <a (click)=\"saveCouponForDeleteOrUpdate(coupon)\" class=\"btn btn-primary\" data-toggle=\"modal\" data-target=\"#deletCouponByTypeModalCenter\">Delete</a>\n              <a (click)=\"saveCouponForDeleteOrUpdate(coupon)\" class=\"btn btn-primary\" data-toggle=\"collapse\" data-target=\"#updateCouponByTypeModalCenter\"\n                aria-controls=\"#updateCouponByTypeModalCenter\">Update</a>\n\n\n            </div>\n            <div class=\"card-footer text-muted\">\n\n            </div>\n          </div>\n\n        </div>\n\n        <!--show coupon by type cards end -->\n        <!--ask if to delete coupon Modal by type start -->\n        <div class=\"modal fade\" id=\"deletCouponByTypeModalCenter\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"deletCouponByTypeModalCenterTitle\"\n          aria-hidden=\"true\">\n          <div class=\"modal-dialog modal-dialog-centered\" role=\"document\">\n            <div class=\"modal-content\">\n              <div class=\"modal-header\">\n                <h5 class=\"modal-title\" id=\"deletCouponByTypeModalCenterTitle\">Delete Coupon</h5>\n                <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">\n                  <span aria-hidden=\"true\">&times;</span>\n                </button>\n              </div>\n              <div class=\"modal-body\">\n                do you want to delete this Coupon ?\n\n              </div>\n              <div class=\"modal-footer\">\n                <button type=\"button\" class=\"btn btn-secondary\" data-dismiss=\"modal\">Close</button>\n                <button type=\"button\" (click)=onDeleteCoupon() class=\"btn btn-primary\" data-dismiss=\"modal\">Delete</button>\n              </div>\n            </div>\n          </div>\n        </div>\n        <!--ask if to delete coupon Modal by type end -->\n\n\n        <!-- update coupon by type collap start -->\n        <div class=\"collapse\" id=\"updateCouponByTypeModalCenter\">\n          <div class=\"card card-body\">\n            <form #searchCompanyByTypeUpdateCoupon=\"ngForm\" (ngSubmit)=\"onUpdateCoupon()\" novalidate>\n              <!--update coupon-->\n              <div class=\"form-group\">\n                <label for=\"inputCouponTitle\">Title</label>\n                <input type=\"text\" class=\"form-control\" [class.is-invalid]=\"couponByTypeTitle.invalid && couponByTypeTitle.touched \" disabled\n                  name=\"couponByTypeTitle\" #couponByTypeTitle=\"ngModel\" required id=\"couponByTypeTitle-update\" [(ngModel)]=\"updatedCoupon.title\">\n                <small class=\"text-danger\" [class.d-none]=\"couponByTypeTitle.valid || couponByTypeTitle.untouched \">couponType is required</small>\n\n              </div>\n              <div class=\"form-group\">\n                <label for=\"inputCouponStartDate\">startDate</label>\n                <input type=\"date\" class=\"form-control\" [class.is-invalid]=\"couponByTypeStartDate.invalid && couponByTypeStartDate.touched \"\n                  name=\"couponByTypeStartDate\" #couponByTypeStartDate=\"ngModel\" required id=\"couponByTypeStartDate-update\"\n                  [(ngModel)]=\"updatedCoupon.startDate\">\n                <small class=\"text-danger\" [class.d-none]=\"couponByTypeStartDate.valid || couponByTypeStartDate.untouched \">coupon startDate is required</small>\n\n              </div>\n              <div class=\"form-group\">\n                <label for=\"inputCouponEndDate\">endDate</label>\n                <input type=\"date\" class=\"form-control\" [class.is-invalid]=\"couponByTypeEndDate.invalid && couponByTypeEndDate.touched \"\n                  name=\"'couponByTypeEndDate'\" #couponByTypeEndDate=\"ngModel\" required id=\"couponByTypeEndDate-update\" [(ngModel)]=\"updatedCoupon.endDate\">\n                <small class=\"text-danger\" [class.d-none]=\"couponByTypeEndDate.valid || couponByTypeEndDate.untouched \">coupon type is required</small>\n\n              </div>\n\n              <div class=\"form-group\">\n                <label for=\"inputCouponAmount\">Amount</label>\n                <input type=\"number\" class=\"form-control\" [class.is-invalid]=\"couponByTypeAmount.invalid && couponByTypeAmount.touched \"\n                  name=\"couponByTypeAmount\" required #couponByTypeAmount=\"ngModel\" id=\"couponByTypeAmount-update\" [(ngModel)]=\"updatedCoupon.amount\">\\\n                <small class=\"text-danger\" [class.d-none]=\"couponByTypeAmount.valid || couponByTypeAmount.untouched \">coupon Amount is required</small>\n\n              </div>\n              <div class=\"form-group\">\n                <label for=\"inputCouponPrice\">Price</label>\n                <input type=\"number\" class=\"form-control\" [class.is-invalid]=\"couponByTypePrice.invalid && couponByTypePrice.touched \" name=\"couponByTypePrice\"\n                  required #couponByTypePrice=\"ngModel\" id=\"couponByTypePrice-update\" [(ngModel)]=\"updatedCoupon.price\">\n                <small class=\"text-danger\" [class.d-none]=\"couponByTypePrice.valid || couponByTypePrice.untouched \">coupon Price is required</small>\n\n              </div>\n\n              <div class=\"form-group\">\n                <label for=\"inputCouponType\">Coupon type</label>\n                <select class=\"custom-select\" name=\"couponByTypeType\" [class.is-invalid]=\"couponByTypeType.invalid && couponByTypeType.touched \"\n                  required #couponByTypeType=\"ngModel\" id=\"couponByTypeType-update\" [(ngModel)]=\"updatedCoupon.couponType\">\n\n                  <option value=\"RESTURANS\" selected>RESTURANS</option>\n                  <option value=\"ELECTRICITY\">ELECTRICITY</option>\n                  <option value=\"FOOD\">FOOD</option>\n                  <option value=\"HEALTH\">HEALTH</option>\n                  <option value=\"SPORTS\">SPORTS</option>\n                  <option value=\"CAMPING\">CAMPING</option>\n                  <option value=\"TRAVELLING\">TRAVELLING</option>\n                </select>\n                <small class=\"text-danger\" [class.d-none]=\"couponByTypeType.valid || couponByTypeType.untouched \">coupon Price is required</small>\n\n              </div>\n\n              <div class=\"form-group\">\n                <label for=\"inputCouponMassege\">Massege </label>\n                <input type=\"text\" class=\"form-control\" [class.is-invalid]=\"couponByTypeMassege.invalid && couponByTypeMassege.touched \"\n                  name=\"couponByTypeMassege\" required #couponByTypeMassege=\"ngModel\" id=\"couponByTypeMassege-update\" [(ngModel)]=\"updatedCoupon.massage\">\n                <small class=\"text-danger\" [class.d-none]=\"couponByTypeMassege.valid || couponByTypeMassege.untouched \">coupon Price is required</small>\n\n              </div>\n              <div class=\"form-group\">\n                <label for=\"inputCouponImage\">Image </label>\n                <input type=\"text\" class=\"form-control\" [class.is-invalid]=\"couponByTypeImage.invalid && couponByTypeImage.touched \" name=\"couponByTypeImage\"\n                  required #couponByTypeImage=\"ngModel\" id=\"couponByTypeImage-update\" [(ngModel)]=\"updatedCoupon.image\">\n                <small class=\"text-danger\" [class.d-none]=\"couponByTypeImage.valid || couponByTypeImage.untouched \">coupon Price is required</small>\n\n              </div>\n\n              <button [disabled]=\"updataCouponBtnDisabled\" type=\"submit\" class=\"btn btn-primary\" [disabled]=\"couponByTypeTitle.invalid  || couponByTypeStartDate.invalid  ||\n          couponByTypeEndDate.invalid ||couponByTypeAmount.invalid  ||couponByTypeType.invalid  ||couponByTypeMassege.invalid  ||couponByTypeImage.invalid \"\n                data-toggle=\"collapse\" data-target=\"#updateCouponByTypeModalCenter\" aria-controls=\"#updateCouponByTypeModalCenter\">Update</button>\n              \n            </form>\n          </div>\n        </div>\n        <!-- update compny by type collap end -->\n\n      </div>\n      <!--search coupon by type end -->\n\n\n\n\n      <!--search coupon by price start -->\n      <div class=\"tab-pane fade \" id=\"v-pills-get-coupon-by-price\" role=\"tabpanel\" aria-labelledby=\"v-pills-get-coupon-by-price-tab\">\n        <div *ngIf=\"logInSuccess && activateGetCouponByPriceTab \">\n          <!-- search coupon by price input -->\n          <form #searchByCouponPriceForm=\"ngForm\" (ngSubmit)=\"listSpecificCoouponsByPrice()\" novalidate>\n            <div class=\"form-row align-items-center\">\n              <div class=\"col-auto my-1\">\n                Enter coupon Price :\n                <input type=\"number\" name=\"couponPrice\" required #couponPrice=\"ngModel\" id=\"couponPrice-search\" [(ngModel)]=\"serchDataForCouponsByPrice.price\">\n              </div>\n\n              <div class=\"col-auto my-1\">\n                <button type=\"submit\" [disabled]=\"couponPrice.invalid\" class=\"btn btn-primary\">Search</button>\n              </div>\n            </div>\n          </form>\n        </div>\n\n\n        <!-- coupon by Price cards start -->\n        <div *ngIf=\"logInSuccess && specificByPriceCouponsListed \">\n          <div class=\"card text-center\" id=\"couponIdByPrice\" *ngFor=\"let coupon of specificByPriceCouponsList\">\n            <div class=\"card-header\">\n              Coupon title : {{coupon.title}}\n            </div>\n            <div class=\"card-body\">\n              <div class=\"row\">\n                <div class=\"col-4\">\n                  Coupon Start date : {{coupon.startDate}}\n                </div>\n                <div class=\"col-4\">\n                  Coupon End Date : {{coupon.endDate}}\n                </div>\n                <div class=\"col-4\">\n                  Coupon Price : {{coupon.price}}\n                </div>\n\n              </div>\n              <div class=\"row\">\n                <div class=\"col-4\">\n                  Coupon Type : {{coupon.couponType}}\n                </div>\n                <div class=\"col-4\">\n                  Coupon Amount : {{coupon.amount}}\n                </div>\n                <div class=\"col-12\">\n                  Massege : {{coupon.massage}}\n                </div>\n\n\n\n              </div>\n\n              <a (click)=\"saveCouponForDeleteOrUpdate(coupon)\" class=\"btn btn-primary\" data-toggle=\"modal\" data-target=\"#deletCouponByPriceModalCenter\">Delete</a>\n              <a (click)=\"saveCouponForDeleteOrUpdate(coupon)\" class=\"btn btn-primary\" data-toggle=\"collapse\" data-target=\"#updateCouponByPriceModalCenter\"\n                aria-controls=\"#updateCouponByTypeModalCenter\">Update</a>\n\n\n            </div>\n            <div class=\"card-footer text-muted\">\n\n            </div>\n          </div>\n\n        </div>\n\n        <!-- coupon by Price cards end -->\n        <!--ask if to delete coupon Modal by Price start -->\n        <div class=\"modal fade\" id=\"deletCouponByPriceModalCenter\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"deletCouponByPriceModalCenterTitle\"\n          aria-hidden=\"true\">\n          <div class=\"modal-dialog modal-dialog-centered\" role=\"document\">\n            <div class=\"modal-content\">\n              <div class=\"modal-header\">\n                <h5 class=\"modal-title\" id=\"deletCouponByPriceModalCenterTitle\">Delete Coupon</h5>\n                <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">\n                  <span aria-hidden=\"true\">&times;</span>\n                </button>\n              </div>\n              <div class=\"modal-body\">\n                do you want to delete this Coupon ?\n\n              </div>\n              <div class=\"modal-footer\">\n                <button type=\"button\" class=\"btn btn-secondary\" data-dismiss=\"modal\">Close</button>\n                <button type=\"button\" (click)=onDeleteCoupon() class=\"btn btn-primary\" data-dismiss=\"modal\">Delete</button>\n              </div>\n            </div>\n          </div>\n        </div>\n        <!--ask if to delete coupon Modal by price end -->\n\n\n        <!-- update coupon by price collap start -->\n        <div class=\"collapse\" id=\"updateCouponByPriceModalCenter\">\n          <div class=\"card card-body\">\n            <form #searchCompanyByPriceUpdateCoupon=\"ngForm\" (ngSubmit)=\"onUpdateCoupon()\" novalidate>\n              <!--update coupon-->\n              <div class=\"form-group\">\n                <label for=\"inputCouponTitle\">Title</label>\n                <input type=\"text\" class=\"form-control\" [class.is-invalid]=\"couponByPriceTitle.invalid && couponByPriceTitle.touched \" disabled\n                  name=\"couponByPriceTitle\" #couponByPriceTitle=\"ngModel\" required id=\"couponByPriceTitle-update\" [(ngModel)]=\"updatedCoupon.title\">\n                <small class=\"text-danger\" [class.d-none]=\"couponByPriceTitle.valid || couponByPriceTitle.untouched \">title is required</small>\n\n              </div>\n              <div class=\"form-group\">\n                <label for=\"inputCouponStartDate\">startDate</label>\n                <input type=\"date\" class=\"form-control\" [class.is-invalid]=\"couponByPriceStartDate.invalid && couponByPriceStartDate.touched \"\n                  name=\"couponByPriceStartDate\" #couponByPriceStartDate=\"ngModel\" required id=\"couponByPriceStartDate-update\"\n                  [(ngModel)]=\"updatedCoupon.startDate\">\n                <small class=\"text-danger\" [class.d-none]=\"couponByPriceStartDate.valid || couponByPriceStartDate.untouched \">startDate is required</small>\n\n              </div>\n              <div class=\"form-group\">\n                <label for=\"inputCouponEndDate\">endDate</label>\n                <input type=\"date\" class=\"form-control\" [class.is-invalid]=\"couponByPriceEndDate.invalid && couponByPriceEndDate.touched \"\n                  name=\"'couponByPriceEndDate'\" #couponByPriceEndDate=\"ngModel\" required id=\"couponByPriceEndDate-update\" [(ngModel)]=\"updatedCoupon.endDate\">\n                <small class=\"text-danger\" [class.d-none]=\"couponByPriceEndDate.valid || couponByPriceEndDate.untouched \">endDate is required</small>\n\n              </div>\n\n              <div class=\"form-group\">\n                <label for=\"inputCouponAmount\">Amount</label>\n                <input type=\"number\" class=\"form-control\" [class.is-invalid]=\"couponByPriceAmount.invalid && couponByPriceAmount.touched \"\n                  name=\"couponByPriceAmount\" required #couponByPriceAmount=\"ngModel\" id=\"couponByPriceAmount-update\" [(ngModel)]=\"updatedCoupon.amount\">\n                <small class=\"text-danger\" [class.d-none]=\"couponByPriceAmount.valid || couponByPriceAmount.untouched \">amount is required</small>\n\n              </div>\n              <div class=\"form-group\">\n                <label for=\"inputCouponPrice\">Price</label>\n                <input type=\"number\" class=\"form-control\" [class.is-invalid]=\"couponByPricePrice.invalid && couponByPricePrice.touched \"\n                  name=\"couponByPricePrice\" required #couponByPricePrice=\"ngModel\" id=\"couponByPricePrice-update\" [(ngModel)]=\"updatedCoupon.price\">\n                <small class=\"text-danger\" [class.d-none]=\"couponByPricePrice.valid || couponByPricePrice.untouched \">Price is required</small>\n\n              </div>\n\n              <div class=\"form-group\">\n                <label for=\"inputCouponType\">Coupon type</label>\n                <select class=\"custom-select\" name=\"couponByPriceType\" [class.is-invalid]=\"couponByPriceType.invalid && couponByPriceType.touched \"\n                  required #couponByPriceType=\"ngModel\" id=\"couponByPriceType-update\" [(ngModel)]=\"updatedCoupon.couponType\">\n\n                  <option value=\"RESTURANS\" selected>RESTURANS</option>\n                  <option value=\"ELECTRICITY\">ELECTRICITY</option>\n                  <option value=\"FOOD\">FOOD</option>\n                  <option value=\"HEALTH\">HEALTH</option>\n                  <option value=\"SPORTS\">SPORTS</option>\n                  <option value=\"CAMPING\">CAMPING</option>\n                  <option value=\"TRAVELLING\">TRAVELLING</option>\n                </select>\n                <small class=\"text-danger\" [class.d-none]=\"couponByPriceType.valid || couponByPriceType.untouched \">couponType is required</small>\n\n              </div>\n\n              <div class=\"form-group\">\n                <label for=\"inputCouponMassege\">Massege </label>\n                <input type=\"text\" class=\"form-control\" [class.is-invalid]=\"couponByPriceMassege.invalid && couponByPriceMassege.touched \"\n                  name=\"couponByPriceMassege\" required #couponByPriceMassege=\"ngModel\" id=\"couponByPriceMassege-update\" [(ngModel)]=\"updatedCoupon.massage\">\n                <small class=\"text-danger\" [class.d-none]=\"couponByPriceMassege.valid || couponByPriceMassege.untouched \">massege is required</small>\n\n              </div>\n              <div class=\"form-group\">\n                <label for=\"inputCouponImage\">Image </label>\n                <input type=\"text\" class=\"form-control\" [class.is-invalid]=\"couponByPriceImage.invalid && couponByPriceImage.touched \" name=\"couponByPriceImage\"\n                  required #couponByPriceImage=\"ngModel\" id=\"couponByPriceImage-update\" [(ngModel)]=\"updatedCoupon.image\">\n                <small class=\"text-danger\" [class.d-none]=\"couponByPriceImage.valid || couponByPriceImage.untouched \">image is required</small>\n\n              </div>\n\n              <button [disabled]=\"updataCouponBtnDisabled\" type=\"submit\" class=\"btn btn-primary\" [disabled]=\"couponByPriceTitle.invalid  || couponByPriceStartDate.invalid  ||\n          couponByPriceEndDate.invalid ||couponByPriceAmount.invalid  ||couponByPriceType.invalid  ||couponByPriceMassege.invalid  ||couponByPriceImage.invalid \"\n                data-toggle=\"collapse\" data-target=\"#updateCouponByPriceModalCenter\" aria-controls=\"#updateCouponByPriceModalCenter\">Update</button>\n              \n            </form>\n          </div>\n        </div>\n        <!-- update compny by price collap end -->\n\n      </div>\n      <!--search coupon by price end -->\n\n\n      <!--search coupon by end date start -->\n      <div class=\"tab-pane fade \" id=\"v-pills-get-coupon-by-endDate\" role=\"tabpanel\" aria-labelledby=\"v-pills-get-coupon-by-endDate-tab\">\n        <div *ngIf=\"logInSuccess && activateGetCouponByEndDateTab \">\n          <!-- search coupon by end data input  -->\n          <form #searchByCouponEndDateForm=\"ngForm\" (ngSubmit)=\"listSpecificCoouponsByEndDate()\" novalidate>\n            <div class=\"form-row align-items-center\">\n              <div class=\"col-auto my-1\">\n                Enter coupon End Date :\n                <input type=\"date\" name=\"couponEndDate\" required #couponEndDate=\"ngModel\" id=\"couponEndDate-search\" [(ngModel)]=\"serchDataForCouponsByEndDate.endDate\">\n              </div>\n\n              <div class=\"col-auto my-1\">\n                <button type=\"submit\" [disabled]=\"couponEndDate.invalid\" class=\"btn btn-primary\">Search</button>\n              </div>\n            </div>\n          </form>\n        </div>\n\n\n        <!--show company coupons by EndDate cards start -->\n        <div *ngIf=\"logInSuccess && specificByEndDateCouponsListed \">\n          <div class=\"card text-center\" id=\"couponIdByEndDate\" *ngFor=\"let coupon of specificByEndDateCouponsList\">\n            <div class=\"card-header\">\n              Coupon title : {{coupon.title}}\n            </div>\n            <div class=\"card-body\">\n              <div class=\"row\">\n                <div class=\"col-4\">\n                  Coupon Start date : {{coupon.startDate}}\n                </div>\n                <div class=\"col-4\">\n                  Coupon End Date : {{coupon.endDate}}\n                </div>\n                <div class=\"col-4\">\n                  Coupon Price : {{coupon.price}}\n                </div>\n\n              </div>\n              <div class=\"row\">\n                <div class=\"col-4\">\n                  Coupon Type : {{coupon.couponType}}\n                </div>\n                <div class=\"col-4\">\n                  Coupon Amount : {{coupon.amount}}\n                </div>\n                <div class=\"col-12\">\n                  Massege : {{coupon.massage}}\n                </div>\n\n\n\n              </div>\n\n              <a (click)=\"saveCouponForDeleteOrUpdate(coupon)\" class=\"btn btn-primary\" data-toggle=\"modal\" data-target=\"#deletCouponByEndDateModalCenter\">Delete</a>\n              <a (click)=\"saveCouponForDeleteOrUpdate(coupon)\" class=\"btn btn-primary\" data-toggle=\"collapse\" data-target=\"#updateCouponByEndDateModalCenter\"\n                aria-controls=\"#updateCouponByTypeModalCenter\">Update</a>\n\n\n            </div>\n            <div class=\"card-footer text-muted\">\n\n            </div>\n          </div>\n\n        </div>\n\n        <!--show company coupons by EndDate cards end -->\n        <!--ask if to delete coupon Modal by EndDate start -->\n        <div class=\"modal fade\" id=\"deletCouponByEndDateModalCenter\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"deletCouponByEndDateModalCenterTitle\"\n          aria-hidden=\"true\">\n          <div class=\"modal-dialog modal-dialog-centered\" role=\"document\">\n            <div class=\"modal-content\">\n              <div class=\"modal-header\">\n                <h5 class=\"modal-title\" id=\"deletCouponByEndDateModalCenterTitle\">Delete Coupon</h5>\n                <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">\n                  <span aria-hidden=\"true\">&times;</span>\n                </button>\n              </div>\n              <div class=\"modal-body\">\n                do you want to delete this Coupon ?\n\n              </div>\n              <div class=\"modal-footer\">\n                <button type=\"button\" class=\"btn btn-secondary\" data-dismiss=\"modal\">Close</button>\n                <button type=\"button\" (click)=onDeleteCoupon() class=\"btn btn-primary\" data-dismiss=\"modal\">Delete</button>\n              </div>\n            </div>\n          </div>\n        </div>\n        <!--ask if to delete coupon Modal by EndDate end -->\n\n\n        <!-- update coupon by EndDate collap start -->\n        <div class=\"collapse\" id=\"updateCouponByEndDateModalCenter\">\n          <div class=\"card card-body\">\n            <form #companyCreatCoupon=\"ngForm\" (ngSubmit)=\"onUpdateCoupon()\" novalidate>\n              <!--update coupon-->\n              <div class=\"form-group\">\n                <label for=\"inputCouponTitle\">Title</label>\n                <input type=\"text\" class=\"form-control\" [class.is-invalid]=\"couponByEndDateTitle.invalid && couponByEndDateTitle.touched\"\n                  disabled name=\"couponByEndDateTitle\" #couponByEndDateTitle=\"ngModel\" required id=\"couponByEndDateTitle-update\"\n                  [(ngModel)]=\"updatedCoupon.title\">\n                <small class=\"text-danger\" [class.d-none]=\"couponByEndDateTitle.valid || couponByEndDateTitle.untouched \">title is required</small>\n\n              </div>\n\n              <div class=\"form-group\">\n                <label for=\"inputCouponStartDate\">startDate</label>\n                <input type=\"date\" class=\"form-control\" [class.is-invalid]=\"couponByEndDateStartDate.invalid && couponByEndDateStartDate.touched \"\n                  name=\"couponByEndDateStartDate\" #couponByEndDateStartDate=\"ngModel\" required id=\"couponByEndDateStartDate-update\"\n                  [(ngModel)]=\"updatedCoupon.startDate\">\n                <small class=\"text-danger\" [class.d-none]=\"couponByEndDateStartDate.valid || couponByEndDateStartDate.untouched \">startDate is required</small>\n\n              </div>\n              <div class=\"form-group\">\n                <label for=\"inputCouponEndDate\">endDate</label>\n                <input type=\"date\" class=\"form-control\" [class.is-invalid]=\"couponByEndDateEndDate.invalid && couponByEndDateEndDate.touched \"\n                  name=\"'couponByEndDateEndDate'\" #couponByEndDateEndDate=\"ngModel\" required id=\"couponByEndDateEndDate-update\"\n                  [(ngModel)]=\"updatedCoupon.endDate\">\n                <small class=\"text-danger\" [class.d-none]=\"couponByEndDateEndDate.valid || couponByEndDateEndDate.untouched \">endDate is required</small>\n\n              </div>\n\n              <div class=\"form-group\">\n                <label for=\"inputCouponAmount\">Amount</label>\n                <input type=\"number\" class=\"form-control\" [class.is-invalid]=\"couponByEndDateAmount.invalid && couponByEndDateAmount.touched \"\n                  name=\"couponByEndDateAmount\" required #couponByEndDateAmount=\"ngModel\" id=\"couponByEndDateAmount-update\"\n                  [(ngModel)]=\"updatedCoupon.amount\">\n                <small class=\"text-danger\" [class.d-none]=\"couponByEndDateAmount.valid || couponByEndDateAmount.untouched \">amount is required</small>\n\n              </div>\n              <div class=\"form-group\">\n                <label for=\"inputCouponPrice\">Price</label>\n                <input type=\"number\" class=\"form-control\" [class.is-invalid]=\"couponByEndDatePrice.invalid && couponByEndDatePrice.touched \"\n                  name=\"couponByEndDatePrice\" required #couponByEndDatePrice=\"ngModel\" id=\"couponByEndDatePrice-update\" [(ngModel)]=\"updatedCoupon.price\">\n                <small class=\"text-danger\" [class.d-none]=\"couponByEndDatePrice.valid || couponByEndDatePrice.untouched \">Price is required</small>\n\n              </div>\n\n              <div class=\"form-group\">\n                <label for=\"inputCouponType\">Coupon type</label>\n                <select class=\"custom-select\" [class.is-invalid]=\"couponByEndDateType.invalid && couponByEndDateType.touched \" name=\"couponByEndDateType\"\n                  required #couponByEndDateType=\"ngModel\" id=\"couponByEndDateType-update\" [(ngModel)]=\"updatedCoupon.couponType\">\n\n                  <option value=\"RESTURANS\" selected>RESTURANS</option>\n                  <option value=\"ELECTRICITY\">ELECTRICITY</option>\n                  <option value=\"FOOD\">FOOD</option>\n                  <option value=\"HEALTH\">HEALTH</option>\n                  <option value=\"SPORTS\">SPORTS</option>\n                  <option value=\"CAMPING\">CAMPING</option>\n                  <option value=\"TRAVELLING\">TRAVELLING</option>\n                </select>\n                <small class=\"text-danger\" [class.d-none]=\"couponByEndDateType.valid || couponByEndDateType.untouched \">couponType is required</small>\n\n              </div>\n\n              <div class=\"form-group\">\n                <label for=\"inputCouponMassege\">Massege </label>\n                <input type=\"text\" class=\"form-control\" [class.is-invalid]=\"couponByEndDateMassege.invalid && couponByEndDateMassege.touched \"\n                  name=\"couponByEndDateMassege\" required #couponByEndDateMassege=\"ngModel\" id=\"couponByEndDateMassege-update\"\n                  [(ngModel)]=\"updatedCoupon.massage\">\n                <small class=\"text-danger\" [class.d-none]=\"couponByEndDateMassege.valid || couponByEndDateMassege.untouched \">massage is required</small>\n              </div>\n              <div class=\"form-group\">\n                <label for=\"inputCouponImage\">Image </label>\n                <input type=\"text\" class=\"form-control\" [class.is-invalid]=\"couponByEndDateImage.invalid && couponByEndDateImage.touched \"\n                  name=\"couponByEndDateImage\" required #couponByEndDateImage=\"ngModel\" id=\"couponByEndDateImage-update\" [(ngModel)]=\"updatedCoupon.image\">\n                <small class=\"text-danger\" [class.d-none]=\"couponByEndDateImage.valid || couponByEndDateImage.untouched \">image is required</small>\n\n              </div>\n\n              <button [disabled]=\"updataCouponBtnDisabled\" type=\"submit\" class=\"btn btn-primary\" [disabled]=\"couponByEndDateTitle.invalid  || couponByEndDateStartDate.invalid  ||\n          couponByEndDateEndDate.invalid ||couponByEndDateAmount.invalid  ||couponByEndDateType.invalid  ||couponByEndDateMassege.invalid  ||couponByEndDateImage.invalid \"\n                data-toggle=\"collapse\" data-target=\"#updateCouponByEndDateModalCenter\" aria-controls=\"#updateCouponByEndDateModalCenter\">Update</button>\n              \n            </form>\n          </div>\n        </div>\n        <!-- update coupon by EndDate collap end -->\n\n      </div>\n\n\n      <!-- company income list start -->\n      <div class=\"tab-pane fade show active\" active id=\"v-pills-list-income\" role=\"tabpanel\" aria-labelledby=\"v-pills-list-income-tab\">\n\n\n\n        <!-- company income list start -->\n\n        <div *ngIf=\"companyIncomeListed && logInSuccess\">\n          <div class=\"card card-body\">\n            <div class=\"row\">\n              <div class=\"col-sm\">\n                actions Description\n              </div>\n              <div class=\"col-sm\">\n                amount\n              </div>\n\n            </div>\n            <div class=\"container\" *ngFor=\"let income of companyIncomeList\">\n              <div class=\"row\">\n                <div class=\"col-sm\">\n                  <div *ngIf=\"income.descrption === 'COMPANY_NEW_COUPON' else  elseMassege \">\n                    {{income.name}} creat new coupon\n                  </div>\n                  <ng-template #elseMassege>\n                    {{income.name}} update coupon\n\n                  </ng-template>\n                </div>\n                <div class=\"col-sm\">\n                  {{income.amount}}\n                </div>\n\n\n              </div>\n            </div>\n          </div>\n        </div>\n\n        <!-- company income list end -->\n      </div>\n    </div>\n  </div>\n\n\n\n\n</div>"

/***/ }),

/***/ "./src/app/company/company.component.ts":
/*!**********************************************!*\
  !*** ./src/app/company/company.component.ts ***!
  \**********************************************/
/*! exports provided: CompanyComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CompanyComponent", function() { return CompanyComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _log_in_data__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ../log-in-data */ "./src/app/log-in-data.ts");
/* harmony import */ var _company_http_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../company-http.service */ "./src/app/company-http.service.ts");
/* harmony import */ var _coupon__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../coupon */ "./src/app/coupon.ts");
/* harmony import */ var _serch_data_for_coupons__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ../serch-data-for-coupons */ "./src/app/serch-data-for-coupons.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};





var CompanyComponent = /** @class */ (function () {
    function CompanyComponent(companyHttpServic) {
        this.companyHttpServic = companyHttpServic;
        // login
        this.logInId = null;
        this.logInFailed = false;
        this.logInModel = new _log_in_data__WEBPACK_IMPORTED_MODULE_1__["LogInData"](this.logInId, "", "");
        this.logInSuccess = false;
        this.logInResponseMassege = "";
        this.logInBtnClickedDisabled = false;
        // coupons
        this.couponModel = new _coupon__WEBPACK_IMPORTED_MODULE_3__["Coupon"]("", null, null, null, "", "", null, "");
        this.creatCouponBtnClickedDisabled = false;
        this.creatCouponResponseMassege = "";
        this.creatCouponMassegeError = false;
        this.couponsList = new Array();
        this.couponsListed = false;
        this.specificByTypeCouponsList = new Array();
        this.specificByTypeCouponsListed = false;
        this.couponCreatedSuccessfully = false;
        this.couponCreatedFailed = false;
        this.timpCoupon = new _coupon__WEBPACK_IMPORTED_MODULE_3__["Coupon"]("", null, null, null, null, null, null, null);
        this.updatedCoupon = new _coupon__WEBPACK_IMPORTED_MODULE_3__["Coupon"]("", null, null, null, null, null, null, null);
        this.updataCouponBtnDisabled = false;
        this.serchDataForCoupons = new _serch_data_for_coupons__WEBPACK_IMPORTED_MODULE_4__["SerchDataForCoupons"](null, null, null);
        this.serchDataForCouponsByPrice = new _serch_data_for_coupons__WEBPACK_IMPORTED_MODULE_4__["SerchDataForCoupons"](null, null, null);
        this.serchDataForCouponsByEndDate = new _serch_data_for_coupons__WEBPACK_IMPORTED_MODULE_4__["SerchDataForCoupons"](null, null, null);
        this.specificByPriceCouponsList = new Array();
        this.specificByPriceCouponsListed = false;
        this.specificByEndDateCouponsList = new Array();
        this.specificByEndDateCouponsListed = false;
        this.activateGetCouponByPriceTab = false;
        this.activateGetCouponByTypeTab = false;
        this.activateGetCouponByEndDateTab = false;
        // company income
        this.companyIncomeList = {};
        this.companyIncomeListed = false;
    }
    CompanyComponent.prototype.ngOnInit = function () {
    };
    ;
    ;
    // log in function
    // this function sends company log in data to the server
    CompanyComponent.prototype.onSubmit = function () {
        var _this = this;
        this.logInBtnClickedDisabled = true;
        this.companyHttpServic.companyLogIn(this.logInModel)
            .subscribe(function (data) { return _this.logInResponseData(data); }, function (error) { return console.log("error", error); });
    };
    //receive log in response data from the server
    // if the log in Succeeded the company work space well appear and the log in form well disappear
    // if the log in failed a massage well appear for the client explaining what happened
    CompanyComponent.prototype.logInResponseData = function (data) {
        this.logInResponseMassege = data.response.logInResponseMassege;
        if (this.logInResponseMassege === "LOGINSUCCESS" || this.logInResponseMassege === "ALREADYLOGGEDIN") {
            this.logInSuccess = true;
            this.logInId = data.response.id;
            this.logInFailed = false;
        }
        if (this.logInResponseMassege === "LOGINFAILED") {
            this.logInFailed = true;
        }
        this.logInBtnClickedDisabled = false;
    };
    // sends the client authorization id to log out from the server
    CompanyComponent.prototype.companyLogOut = function () {
        var _this = this;
        var requestData = {
            clientId: this.logInId
        };
        this.companyHttpServic.companyLogOut(requestData)
            .subscribe(function (data) { return _this.logOutResponseData(data); }, function (error) { return console.log(error); });
    };
    // receive the data from the server response 
    // hide the company work space
    CompanyComponent.prototype.logOutResponseData = function (data) {
        this.logInSuccess = false;
    };
    /////////////////////////////////////////////////////////////////////////////////////
    //sends the coupon data to creat new coupon on the server 
    CompanyComponent.prototype.onCreatCoupon = function () {
        var _this = this;
        this.creatCouponBtnClickedDisabled = true;
        this.couponCreatedSuccessfully = false;
        var requestData = {
            clientId: this.logInId,
            coupon: this.couponModel
        };
        this.companyHttpServic.creatCoupon(requestData)
            .subscribe(function (data) { return _this.onCreatCouponData(data); }, function (error) { return console.log(error); });
    };
    // receives the server response 
    // if the coupon  created successfully a massage well appear explains that it success 
    // if the server faild to creat the coupon a massage well appear explains that it failed
    CompanyComponent.prototype.onCreatCouponData = function (data) {
        if (data.responseMessage === "COUPONISCREATED") {
            this.couponCreatedSuccessfully = true;
            this.couponCreatedFailed = false;
        }
        if (data.responseMessage === "COUPONAMEISALREADYUSED") {
            this.couponCreatedFailed = true;
            this.couponCreatedSuccessfully = false;
        }
        this.creatCouponBtnClickedDisabled = false;
    };
    // sends the company authorization id to get all the company coupons
    CompanyComponent.prototype.listAllCoupons = function () {
        var _this = this;
        var requestData = {
            clientId: this.logInId
        };
        this.companyHttpServic.listAllCoupons(requestData)
            .subscribe(function (data) { return _this.listAllCouponsData(data); });
    };
    // receive the response from the server
    // save the list of the company coupons to local variable
    // shows the company coupons in the work space
    CompanyComponent.prototype.listAllCouponsData = function (data) {
        this.couponsList = data.response;
        this.couponsListed = true;
    };
    // saves the coupon in a temporary variable before update of delete
    CompanyComponent.prototype.saveCouponForDeleteOrUpdate = function (coupon) {
        this.timpCoupon = coupon;
        this.updatedCoupon = coupon;
    };
    // sends the coupon data from the temporary variable 
    // and the authorization id to the server to update the coupon
    CompanyComponent.prototype.onUpdateCoupon = function () {
        var _this = this;
        this.updataCouponBtnDisabled = true;
        var requestData = {
            clientId: this.logInId,
            coupon: this.updatedCoupon
        };
        this.companyHttpServic.updateCoupon(requestData)
            .subscribe(function (data) { return _this.onUpdateCouponData(data); });
    };
    // receives the response from the server
    //  update the coupon list in the work space
    CompanyComponent.prototype.onUpdateCouponData = function (data) {
        this.listAllCoupons();
        this.updataCouponBtnDisabled = false;
    };
    // sends the authorization id and the coupon data
    // from the temporary variable to the srver to delete it
    CompanyComponent.prototype.onDeleteCoupon = function () {
        var _this = this;
        var requestData = {
            clientId: this.logInId,
            coupon: this.timpCoupon
        };
        this.companyHttpServic.deleteCoupon(requestData)
            .subscribe(function (data) { return _this.deleteCouponData(data); });
    };
    // receives the response from the server and change the list 
    // of the coumpany coupons
    CompanyComponent.prototype.deleteCouponData = function (data) {
        this.listAllCoupons();
    };
    // company incom function
    //sends the coumpany authorization id to get the coumpany income list
    // list data
    CompanyComponent.prototype.listAllCompanyIncome = function () {
        var _this = this;
        var requestData = {
            clientId: this.logInId
        };
        this.companyHttpServic.getCompanyIncome(requestData)
            .subscribe(function (data) { return _this.listAllCompanyIncomeData(data); });
    };
    // receives the response data from the server 
    // saves the list of the company income to local variable 
    // shows the list of income in the work space
    CompanyComponent.prototype.listAllCompanyIncomeData = function (data) {
        this.companyIncomeList = data.response;
        this.companyIncomeListed = true;
    };
    //sends the authorization id and coupon type to the srver 
    // to search for coupons with same type
    CompanyComponent.prototype.listSpecificCouponsByType = function () {
        var _this = this;
        var requestData = {
            clientId: this.logInId,
            specificCouponData: this.serchDataForCoupons
        };
        this.companyHttpServic.getSpecificCoupons(requestData)
            .subscribe(function (data) { return _this.listSpecificCouponsData(data); });
    };
    // receive list of the searched coupons from the server
    // save the list to local variable 
    // show the list in the company work space
    CompanyComponent.prototype.listSpecificCouponsData = function (data) {
        this.specificByTypeCouponsList = data.response;
        this.specificByTypeCouponsListed = true;
    };
    //sends the authorization id and coupon price to the srver 
    // to search for coupons with same or less price
    CompanyComponent.prototype.listSpecificCoouponsByPrice = function () {
        var _this = this;
        var requestData = {
            clientId: this.logInId,
            specificCouponData: this.serchDataForCouponsByPrice
        };
        this.companyHttpServic.getSpecificCoupons(requestData)
            .subscribe(function (data) { return _this.listSpecificCouponsByPriceData(data); });
    };
    // receive list of the searched coupons from the server
    // save the list to local variable 
    // show the list in the company work space
    CompanyComponent.prototype.listSpecificCouponsByPriceData = function (data) {
        this.specificByPriceCouponsList = data.response;
        this.specificByPriceCouponsListed = true;
    };
    //sends the authorization id and coupon endDate to the srver 
    // to search for coupons with same endDate
    CompanyComponent.prototype.listSpecificCoouponsByEndDate = function () {
        var _this = this;
        this.activateSearchTabs(false, false, true);
        var requestData = {
            clientId: this.logInId,
            specificCouponData: this.serchDataForCouponsByEndDate
        };
        this.companyHttpServic.getSpecificCoupons(requestData)
            .subscribe(function (data) { return _this.listSpecificCouponsByEndDateData(data); });
    };
    // receive list of the searched coupons from the server
    // save the list to local variable 
    // show the list in the company work space
    CompanyComponent.prototype.listSpecificCouponsByEndDateData = function (data) {
        this.specificByEndDateCouponsList = data.response;
        this.specificByEndDateCouponsListed = true;
    };
    // show the search by type form 
    CompanyComponent.prototype.activateSearchByType = function () {
        this.activateSearchTabs(true, false, false);
    };
    // show the search by price form
    CompanyComponent.prototype.activateSearchByPrice = function () {
        this.activateSearchTabs(false, true, false);
    };
    // show the search by endDate form
    CompanyComponent.prototype.activateSearchByEndDate = function () {
        this.activateSearchTabs(false, false, true);
    };
    CompanyComponent.prototype.activateSearchTabs = function (isByTypeSearch, isByPriceSearch, isByEndDateSearch) {
        this.activateGetCouponByPriceTab = isByPriceSearch;
        this.activateGetCouponByTypeTab = isByTypeSearch;
        this.activateGetCouponByEndDateTab = isByEndDateSearch;
    };
    CompanyComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-company',
            template: __webpack_require__(/*! ./company.component.html */ "./src/app/company/company.component.html"),
            styles: [__webpack_require__(/*! ./company.component.css */ "./src/app/company/company.component.css")]
        }),
        __metadata("design:paramtypes", [_company_http_service__WEBPACK_IMPORTED_MODULE_2__["CompanyHttpService"]])
    ], CompanyComponent);
    return CompanyComponent;
}());



/***/ }),

/***/ "./src/app/coupon.ts":
/*!***************************!*\
  !*** ./src/app/coupon.ts ***!
  \***************************/
/*! exports provided: Coupon */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "Coupon", function() { return Coupon; });
var Coupon = /** @class */ (function () {
    function Coupon(title, startDate, endDate, amount, couponType, massage, price, image) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
        this.couponType = couponType;
        this.massage = massage;
        this.price = price;
        this.image = image;
    }
    return Coupon;
}());



/***/ }),

/***/ "./src/app/customer-http.service.ts":
/*!******************************************!*\
  !*** ./src/app/customer-http.service.ts ***!
  \******************************************/
/*! exports provided: CustomerHttpService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CustomerHttpService", function() { return CustomerHttpService; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var CustomerHttpService = /** @class */ (function () {
    function CustomerHttpService(customerHttp) {
        this.customerHttp = customerHttp;
        this.baseUrl = "customerRest";
    }
    // sends the log in data to the server to get the authorization id and log in successfully
    CustomerHttpService.prototype.customerLogIn = function (logIn) {
        return this.customerHttp.post(this.baseUrl + "/customerlogIn", logIn);
    };
    CustomerHttpService.prototype.customerLogOut = function (logInIdData) {
        return this.customerHttp.post(this.baseUrl + "/customerLogOut", logInIdData);
    };
    CustomerHttpService.prototype.customerPurechasCoupon = function (requestData) {
        return this.customerHttp.post(this.baseUrl + "/purchaseCoupon", requestData);
    };
    CustomerHttpService.prototype.listCustomrCoupon = function (requestData) {
        return this.customerHttp.post(this.baseUrl + "/listAllCustomerCoupons", requestData);
    };
    CustomerHttpService.prototype.listCustomrSpecificCoupon = function (requestData) {
        return this.customerHttp.post(this.baseUrl + "/getCouponsByCouponTypeOrPrice", requestData);
    };
    CustomerHttpService.prototype.getCoupons = function () {
        return this.customerHttp.get(this.baseUrl + "/getAllCoupon");
    };
    CustomerHttpService = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Injectable"])({
            providedIn: 'root'
        }),
        __metadata("design:paramtypes", [_angular_common_http__WEBPACK_IMPORTED_MODULE_1__["HttpClient"]])
    ], CustomerHttpService);
    return CustomerHttpService;
}());



/***/ }),

/***/ "./src/app/customer.ts":
/*!*****************************!*\
  !*** ./src/app/customer.ts ***!
  \*****************************/
/*! exports provided: Customer */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "Customer", function() { return Customer; });
var Customer = /** @class */ (function () {
    function Customer(name, password) {
        this.name = name;
        this.password = password;
    }
    return Customer;
}());



/***/ }),

/***/ "./src/app/customer/customer.component.css":
/*!*************************************************!*\
  !*** ./src/app/customer/customer.component.css ***!
  \*************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "h1{\r\n    text-align: center;\r\n}\r\n.logIn{\r\n    margin: 2em;\r\n    margin-right: 60%;\r\n}"

/***/ }),

/***/ "./src/app/customer/customer.component.html":
/*!**************************************************!*\
  !*** ./src/app/customer/customer.component.html ***!
  \**************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<h1>Customer Service</h1>\n\n\n<!-- customer log in form  start -->\n\n<div class=\"logIn \" [hidden]=\"logInSuccess\">\n  <form #cusomerLogIn=\"ngForm\" (ngSubmit)=\"onCustomerLogIn()\" novalidate>\n\n    <div>\n      <div class=\"form-group\">\n\n        <label for=\"inputUserName\" [class.text-danger]=\"name.invalid && name.touched\">User Name</label>\n        <input type=\"text\" class=\"form-control\" [class.is-invalid]=\"name.invalid && name.touched\" name=\"Name\" required #name=\"ngModel\"\n          id=\"LogInUserName\" [(ngModel)]=\"logInModel.userName\">\n        <small class=\"text-danger\" [class.d-none]=\"name.valid || name.untouched \">user name is required</small>\n\n      </div>\n      <div class=\"form-group\">\n        <label for=\"inputUserName\" [class.text-danger]=\"password.invalid && password.touched\">Password</label>\n        <input type=\"password\" [class.is-invalid]=\"password.invalid && password.touched\" class=\"form-control\" name=\"password\" required\n          #password=\"ngModel\" id=\"LogInPassword\" [(ngModel)]=\"logInModel.password\">\n        <small class=\"text-danger\" [class.d-none]=\"password.valid || password.untouched \">password is required</small>\n      </div>\n      <button type=\"submit\" class=\"btn btn-primary\" [disabled]=\"name.invalid || name.untouched || password.invalid || logInBtnClickedDisabled \">logIn</button>\n      <div>\n        <small class=\"text-danger\" [class.d-none]=\"!logInFailed \">log in failed wrong user name or password</small>\n        \n      </div>\n    </div>\n  </form>\n</div>\n<div>\n  <button (click)=\"customerLogOut()\" [hidden]=!logInSuccess>log out</button>\n</div>\n<!-- customer log in form  end -->\n\n\n\n\n<!-- customer main content  start -->\n<div class=\"row\" [hidden]=!logInSuccess>\n  <!-- links for the content -->\n  <div class=\"col-3\">\n    <div class=\"nav flex-column nav-pills\" id=\"v-pills-tab\" role=\"tablist\" aria-orientation=\"vertical\">\n      <a class=\"nav-link\" id=\"v-pills-purchase-coupon-tab\" (click)=\"listAllCoupons();\" data-toggle=\"pill\" href=\"#v-pills-purchase-coupon\"\n        role=\"tab\" aria-controls=\"v-pills-purchase-coupon\" aria-selected=\"true\">Coupons</a>\n      <a class=\"nav-link\" id=\"v-pills-get-my-coupon-tab\" (click)=\"listMyCoupons();activateGetMyCoupon() \" data-toggle=\"pill\" href=\"#v-pills-get-my-coupon\"\n        role=\"tab\" aria-controls=\"v-pills-get-my-coupon\" aria-selected=\"false\">My Coupons </a>\n      <a class=\"nav-link\" id=\"v-pills-get-coupon-by-type-tab\" (click)=\"activateGetMyCouponByType() \" data-toggle=\"pill\" href=\"#v-pills-get-coupon-by-type\"\n        role=\"tab\" aria-controls=\"v-pills-get-coupon-by-type\" aria-selected=\"false\">coupons by type</a>\n      <a class=\"nav-link\" id=\"v-pills-get-coupon-by-price-tab\" (click)=\"activateGetMyCouponByPrice() \" data-toggle=\"pill\" href=\"#v-pills-get-coupon-by-price\"\n        role=\"tab\" aria-controls=\"v-pills-get-coupon-by-price\" aria-selected=\"false\">coupons by price</a>\n    </div>\n  </div>\n  <!-- customer work space -->\n  <div class=\"col-9\">\n    <div class=\"tab-content\" id=\"v-pills-tabContent\">\n\n      <!-- show all coupon for the customer to purchase start-->\n      <div class=\"tab-pane fade show active\" active id=\"v-pills-purchase-coupon\" role=\"tabpanel\" aria-labelledby=\"v-pills-purchase-coupon-tab\">\n\n        <!-- coupons show cards start -->\n        <!-- if log in not success and the coupon still not listed this data wont show for the client -->\n        <div *ngIf=\"logInSuccess && couponsListed \">\n          <div class=\"card text-center\" id=\"couponId\" *ngFor=\"let coupon of couponList\">\n            <div class=\"card-header\">\n              Coupon title : {{coupon.title}}\n            </div>\n            <div class=\"card-body\">\n              <div class=\"row\">\n                <div class=\"col-4\">\n                  Coupon Start date : {{coupon.startDate}}\n                </div>\n                <div class=\"col-4\">\n                  Coupon End Date : {{coupon.endDate}}\n                </div>\n                <div class=\"col-4\">\n                  Coupon Price : {{coupon.price}}\n                </div>\n\n              </div>\n              <div class=\"row\">\n                <div class=\"col-4\">\n                  Coupon Type : {{coupon.couponType}}\n                </div>\n                <div class=\"col-4\">\n                  Coupon Amount : {{coupon.amount}}\n                </div>\n                <div class=\"col-12\">\n                  Massege : {{coupon.massage}}\n                </div>\n              </div>\n\n              <a (click)=\"saveCouponForPurchase(coupon)\" class=\"btn btn-primary\" data-toggle=\"modal\" data-target=\"#purchaseCouponModalCenter\">Purchase Coupon</a>\n\n\n\n            </div>\n            <div class=\"card-footer text-muted\">\n\n            </div>\n          </div>\n\n        </div>\n\n        <!-- coupon cards end -->\n\n        <!--ask if the customer want to purchase the coupon Modal  start -->\n        <div class=\"modal fade\" id=\"purchaseCouponModalCenter\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"purchaseCouponModalCenterTitle\"\n          aria-hidden=\"true\">\n          <div class=\"modal-dialog modal-dialog-centered\" role=\"document\">\n            <div class=\"modal-content\">\n              <div class=\"modal-header\">\n                <h5 class=\"modal-title\" id=\"purchaseCouponModalCenterTitle\">Delete Coupon</h5>\n                <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">\n                  <span aria-hidden=\"true\">&times;</span>\n                </button>\n              </div>\n              <div class=\"modal-body\">\n                do you want to purchase this Coupon ?\n\n              </div>\n              <div class=\"modal-footer\">\n                <button type=\"button\" class=\"btn btn-secondary\" data-dismiss=\"modal\">Close</button>\n                <button type=\"button\" (click)=purchaseCoupon() class=\"btn btn-primary\" data-dismiss=\"modal\">Purchase</button>\n              </div>\n            </div>\n          </div>\n        </div>\n        <!-- ask if the customer want to purchase the coupon Modal end -->\n      </div>\n      <!-- show all coupon for the customer to purchase end -->\n\n\n\n      <!-- show custmers coupon start -->\n      <div class=\"tab-pane fade show active\" active id=\"v-pills-get-my-coupon\" role=\"tabpanel\" aria-labelledby=\"v-pills-get-my-coupon-tab\">\n        <!-- coupon cards start -->\n        <!-- if log in not success , my coupon listed and my coupons tab  not clicked this data wont show for the client -->\n        <div *ngIf=\"logInSuccess && myCouponsListed && getMyCouponActivate \">\n          <div class=\"card text-center\" id=\"myCouponId\" *ngFor=\"let coupon of myCouponsList\">\n            <div class=\"card-header\">\n              Coupon title : {{coupon.title}}\n            </div>\n            <div class=\"card-body\">\n              <div class=\"row\">\n                <div class=\"col-4\">\n                  Coupon Start date : {{coupon.startDate}}\n                </div>\n                <div class=\"col-4\">\n                  Coupon End Date : {{coupon.endDate}}\n                </div>\n                <div class=\"col-4\">\n                  Coupon Price : {{coupon.price}}\n                </div>\n\n              </div>\n              <div class=\"row\">\n                <div class=\"col-4\">\n                  Coupon Type : {{coupon.couponType}}\n                </div>\n                <div class=\"col-4\">\n                  Coupon Amount : {{coupon.amount}}\n                </div>\n                <div class=\"col-12\">\n                  Massege : {{coupon.massage}}\n                </div>\n                <div class=\"col-12\">\n                  Image : {{coupon.image}}\n                </div>\n              </div>\n\n\n\n\n            </div>\n            <div class=\"card-footer text-muted\">\n\n            </div>\n          </div>\n\n        </div>\n\n        <!-- coupon cards end -->\n\n      </div>\n      <!-- show custmers coupon end -->\n\n\n\n      <!-- show custmer coupon by type start -->\n      <div class=\"tab-pane fade show active\" active id=\"v-pills-get-coupon-by-type\" role=\"tabpanel\" aria-labelledby=\"v-pills-get-coupon-by-type-tab\">\n\n        <!-- if log in not success and the coupon by type tab not clicked this data wont show for the client -->\n        <div *ngIf=\"logInSuccess  && getMyCouponByTypeActivate\">\n\n          <form #searchByCouponTypeForm=\"ngForm\" (ngSubmit)=\"listMyCouponByType()\" novalidate>\n            <div class=\"form-row align-items-center\">\n              Chose coupon type :\n              <div class=\"col-auto my-1\">\n\n                <select class=\"custom-select mr-sm-2\" [class.is-invalid]=\"couponType.invalid && couponType.touched\" name=\"couponType\" required\n                  #couponType=\"ngModel\" id=\"couponType-search\" [(ngModel)]=\"typeOfTheCoupon.couponType\">\n\n                  <option value=\"RESTURANS\" selected>RESTURANS</option>\n                  <option value=\"ELECTRICITY\">ELECTRICITY</option>\n                  <option value=\"FOOD\">FOOD</option>\n                  <option value=\"HEALTH\">HEALTH</option>\n                  <option value=\"SPORTS\">SPORTS</option>\n                  <option value=\"CAMPING\">CAMPING</option>\n                  <option value=\"TRAVELLING\">TRAVELLING</option>\n                </select>\n              </div>\n\n              <div class=\"col-auto my-1\">\n                <button type=\"submit\" [disabled]=\"couponType.invalid\" class=\"btn btn-primary\">Search</button>\n              </div>\n              <small class=\"text-danger\" [class.d-none]=\"couponType.valid || couponType.untouched \">couponType is required</small>\n\n            </div>\n          </form>\n        </div>\n\n\n        <!-- coupon cards start -->\n        <div *ngIf=\"logInSuccess && myCouponsListedByType \">\n          <div class=\"card text-center\" id=\"myCouponId\" *ngFor=\"let coupon of myCouponsListByType\">\n            <div class=\"card-header\">\n              Coupon title : {{coupon.title}}\n            </div>\n            <div class=\"card-body\">\n              <div class=\"row\">\n                <div class=\"col-4\">\n                  Coupon Start date : {{coupon.startDate}}\n                </div>\n                <div class=\"col-4\">\n                  Coupon End Date : {{coupon.endDate}}\n                </div>\n                <div class=\"col-4\">\n                  Coupon Price : {{coupon.price}}\n                </div>\n\n              </div>\n              <div class=\"row\">\n                <div class=\"col-4\">\n                  Coupon Type : {{coupon.couponType}}\n                </div>\n                <div class=\"col-4\">\n                  Coupon Amount : {{coupon.amount}}\n                </div>\n                <div class=\"col-12\">\n                  Massege : {{coupon.massage}}\n                </div>\n                <div class=\"col-12\">\n                  Image : {{coupon.image}}\n                </div>\n              </div>\n\n\n\n\n            </div>\n            <div class=\"card-footer text-muted\">\n\n            </div>\n          </div>\n\n        </div>\n\n        <!-- coupon cards end -->\n\n      </div>\n      <!-- show custmer coupon by type end -->\n\n      <!-- show custmer coupon by price start -->\n      <div class=\"tab-pane fade show active\" active id=\"v-pills-get-coupon-by-price\" role=\"tabpanel\" aria-labelledby=\"v-pills-get-coupon-by-price-tab\">\n\n        <!-- if log in not success and the coupon by price tab not clicked this data wont show for the client -->\n        <div *ngIf=\"logInSuccess  && getMyCouponByPriceActivate\">\n          <!-- search coupon by price forme start -->\n          <form #searchByCouponPriceForm=\"ngForm\" (ngSubmit)=\"listMyCouponByPrice()\" novalidate>\n            <div class=\"form-row align-items-center\">\n              <div class=\"col-auto my-1\">\n                Enter coupon Price :\n                <input type=\"number\" name=\"couponPrice\" required #couponPrice=\"ngModel\" id=\"couponPrice-search\" [(ngModel)]=\"priceOfTheCoupon.price\">\n              </div>\n\n              <div class=\"col-auto my-1\">\n                <button type=\"submit\" [disabled]=\"couponPrice.invalid\" class=\"btn btn-primary\">Search</button>\n              </div>\n              <small class=\"text-danger\" [class.d-none]=\"couponPrice.valid || couponPrice.untouched \">coupon price is required</small>\n            </div>\n          </form>\n          <!-- search coupon by price forme end -->\n        </div>\n\n\n        <!-- coupon cards start -->\n        <div *ngIf=\"logInSuccess && myCouponsListedByPrice \">\n          <div class=\"card text-center\" id=\"myCouponId\" *ngFor=\"let coupon of myCouponsListByPrice\">\n            <div class=\"card-header\">\n              Coupon title : {{coupon.title}}\n            </div>\n            <div class=\"card-body\">\n              <div class=\"row\">\n                <div class=\"col-4\">\n                  Coupon Start date : {{coupon.startDate}}\n                </div>\n                <div class=\"col-4\">\n                  Coupon End Date : {{coupon.endDate}}\n                </div>\n                <div class=\"col-4\">\n                  Coupon Price : {{coupon.price}}\n                </div>\n\n              </div>\n              <div class=\"row\">\n                <div class=\"col-4\">\n                  Coupon Type : {{coupon.couponType}}\n                </div>\n                <div class=\"col-4\">\n                  Coupon Amount : {{coupon.amount}}\n                </div>\n                <div class=\"col-12\">\n                  Massege : {{coupon.massage}}\n                </div>\n                <div class=\"col-12\">\n                  Image : {{coupon.image}}\n                </div>\n              </div>\n\n\n\n\n            </div>\n            <div class=\"card-footer text-muted\">\n\n            </div>\n          </div>\n\n        </div>\n\n        <!-- coupon cards end -->\n\n      </div>\n      <!-- show custmer coupon by price start -->\n    </div>\n  </div>"

/***/ }),

/***/ "./src/app/customer/customer.component.ts":
/*!************************************************!*\
  !*** ./src/app/customer/customer.component.ts ***!
  \************************************************/
/*! exports provided: CustomerComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CustomerComponent", function() { return CustomerComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _log_in_data__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ../log-in-data */ "./src/app/log-in-data.ts");
/* harmony import */ var _customer_http_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../customer-http.service */ "./src/app/customer-http.service.ts");
/* harmony import */ var _serch_data_for_coupons__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../serch-data-for-coupons */ "./src/app/serch-data-for-coupons.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




var CustomerComponent = /** @class */ (function () {
    function CustomerComponent(customerHttpServic) {
        this.customerHttpServic = customerHttpServic;
        // login
        this.logInId = null;
        this.logInModel = new _log_in_data__WEBPACK_IMPORTED_MODULE_1__["LogInData"](this.logInId, "", "");
        this.logInSuccess = false;
        this.logInResponseMassege = "";
        this.logInBtnClickedDisabled = false;
        this.logInFailed = false;
        //coupons
        this.coupon = {};
        this.couponList = {};
        this.couponsListed = false;
        this.myCouponsList = {};
        this.myCouponsListed = false;
        this.typeOfTheCoupon = new _serch_data_for_coupons__WEBPACK_IMPORTED_MODULE_3__["SerchDataForCoupons"](null, null, null);
        this.priceOfTheCoupon = new _serch_data_for_coupons__WEBPACK_IMPORTED_MODULE_3__["SerchDataForCoupons"](null, null, null);
        this.myCouponsListByType = {};
        this.myCouponsListedByType = false;
        this.myCouponsListByPrice = {};
        this.myCouponsListedByPrice = false;
        this.getMyCouponByTypeActivate = false;
        this.getMyCouponByPriceActivate = false;
        this.getMyCouponActivate = false;
    }
    CustomerComponent.prototype.ngOnInit = function () {
    };
    // when the customer press the log in button the function sendes the data to the server 
    CustomerComponent.prototype.onCustomerLogIn = function () {
        var _this = this;
        this.logInBtnClickedDisabled = true;
        this.customerHttpServic.customerLogIn(this.logInModel)
            .subscribe(function (data) { return _this.logInResponseData(data); }, function (error) { return console.log("error", error); });
    };
    //this function receives the data from the server 
    //if the customer log in was successful(already logged in is successfully logged in) the customer work space we'll  appear and log in input we'll be hidden
    //if the customer log in was failed the client well show appropriate massage
    CustomerComponent.prototype.logInResponseData = function (data) {
        // save the log in response massage from the data base
        this.logInResponseMassege = data.response.logInResponseMassege;
        if (this.logInResponseMassege === "LOGINSUCCESS" || this.logInResponseMassege === "ALREADYLOGGEDIN") {
            this.logInSuccess = true;
            this.logInId = data.response.id;
            this.logInFailed = false;
        }
        if (this.logInResponseMassege === "LOGINFAILED") {
            this.logInFailed = true;
            this.logInId = null;
        }
        this.logInBtnClickedDisabled = false;
    };
    // when the custmer presses the log out button sends the login id(authorization id ) to the server
    CustomerComponent.prototype.customerLogOut = function () {
        var _this = this;
        var requestData = {
            clientId: this.logInId
        };
        this.customerHttpServic.customerLogOut(requestData)
            .subscribe(function (data) { return _this.customerLogOutData(data); });
    };
    // receives the data from the server 
    // and logging out the user in the client
    // shows the log in input and hide the customer work space 
    CustomerComponent.prototype.customerLogOutData = function (data) {
        this.logInId = null;
        this.logInSuccess = false;
    };
    // sends the request to get all the coupons from the server 
    CustomerComponent.prototype.listAllCoupons = function () {
        var _this = this;
        this.customerHttpServic.getCoupons().
            subscribe(function (data) { return _this.listAllCouponData(data); });
    };
    // get the data of all the coupons from the server
    // and save it in array in the client 
    // show the coupon list in the html
    CustomerComponent.prototype.listAllCouponData = function (data) {
        this.couponList = data.response;
        this.couponsListed = true;
    };
    // sendes the Authorization id to the server to get customer purchased coupons
    CustomerComponent.prototype.listMyCoupons = function () {
        var _this = this;
        var requestData = {
            clientId: this.logInId
        };
        this.customerHttpServic.listCustomrCoupon(requestData)
            .subscribe(function (data) { return _this.listMyCouponsData(data); });
    };
    // receives the data from the server 
    // saves the coupons in client array 
    // show the list in the customer work space
    CustomerComponent.prototype.listMyCouponsData = function (data) {
        console.log(data);
        this.myCouponsList = data.response;
        this.myCouponsListed = true;
    };
    // saves the customer from the html in order to purchase it
    CustomerComponent.prototype.saveCouponForPurchase = function (coupon) {
        this.coupon = coupon;
    };
    // sends the authorization id and coupon data for purchase
    // and saves the purchase data in income table in data base 
    CustomerComponent.prototype.purchaseCoupon = function () {
        var _this = this;
        var requestData = {
            clientId: this.logInId,
            coupon: this.coupon
        };
        this.customerHttpServic.customerPurechasCoupon(requestData)
            .subscribe(function (data) { return _this.purchaseCouponData(data); });
    };
    // receives data from the server
    CustomerComponent.prototype.purchaseCouponData = function (data) {
    };
    // sendes the Authorization id and specific coupon data to the server to get customer purchased coupons by type
    CustomerComponent.prototype.listMyCouponByType = function () {
        var _this = this;
        var requestData = {
            clientId: this.logInId,
            specificCouponData: this.typeOfTheCoupon
        };
        this.customerHttpServic.listCustomrSpecificCoupon(requestData)
            .subscribe(function (data) { return _this.listMyCouponByTypeData(data); });
    };
    // receives the data from the server 
    // saves the coupons in client array 
    // show the list in the customer work space
    CustomerComponent.prototype.listMyCouponByTypeData = function (data) {
        this.myCouponsListByType = data.response;
        this.myCouponsListedByType = true;
    };
    // sendes the Authorization id and specific coupon data to the server to get customer purchased coupons by price
    CustomerComponent.prototype.listMyCouponByPrice = function () {
        var _this = this;
        var requestData = {
            clientId: this.logInId,
            specificCouponData: this.priceOfTheCoupon
        };
        this.customerHttpServic.listCustomrSpecificCoupon(requestData)
            .subscribe(function (data) { return _this.listMyCouponByPriceData(data); });
    };
    // receives the data from the server 
    // saves the coupons in client array 
    // show the list in the customer work space
    CustomerComponent.prototype.listMyCouponByPriceData = function (data) {
        this.myCouponsListByPrice = data.response;
        this.myCouponsListedByPrice = true;
    };
    // show the customer list of coupons
    // hide the rest listes
    CustomerComponent.prototype.activateGetMyCouponByType = function () {
        this.getMyCouponByTypeActivate = true;
        this.getMyCouponByPriceActivate = false;
        this.getMyCouponActivate = false;
    };
    // show the customer list of coupons by type
    // hide the rest listes
    CustomerComponent.prototype.activateGetMyCouponByPrice = function () {
        this.getMyCouponByTypeActivate = false;
        this.getMyCouponByPriceActivate = true;
        this.getMyCouponActivate = false;
    };
    // show the customer list of coupons by price
    // hide the rest listes
    CustomerComponent.prototype.activateGetMyCoupon = function () {
        this.getMyCouponByTypeActivate = false;
        this.getMyCouponByPriceActivate = false;
        this.getMyCouponActivate = true;
    };
    CustomerComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-customer',
            template: __webpack_require__(/*! ./customer.component.html */ "./src/app/customer/customer.component.html"),
            styles: [__webpack_require__(/*! ./customer.component.css */ "./src/app/customer/customer.component.css")]
        }),
        __metadata("design:paramtypes", [_customer_http_service__WEBPACK_IMPORTED_MODULE_2__["CustomerHttpService"]])
    ], CustomerComponent);
    return CustomerComponent;
}());



/***/ }),

/***/ "./src/app/log-in-data.ts":
/*!********************************!*\
  !*** ./src/app/log-in-data.ts ***!
  \********************************/
/*! exports provided: LogInData */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "LogInData", function() { return LogInData; });
var LogInData = /** @class */ (function () {
    function LogInData(userId, userName, password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }
    return LogInData;
}());



/***/ }),

/***/ "./src/app/serch-data-for-coupons.ts":
/*!*******************************************!*\
  !*** ./src/app/serch-data-for-coupons.ts ***!
  \*******************************************/
/*! exports provided: SerchDataForCoupons */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "SerchDataForCoupons", function() { return SerchDataForCoupons; });
var SerchDataForCoupons = /** @class */ (function () {
    function SerchDataForCoupons(couponType, price, endDate) {
        this.couponType = couponType;
        this.price = price;
        this.endDate = endDate;
    }
    return SerchDataForCoupons;
}());



/***/ }),

/***/ "./src/environments/environment.ts":
/*!*****************************************!*\
  !*** ./src/environments/environment.ts ***!
  \*****************************************/
/*! exports provided: environment */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "environment", function() { return environment; });
// This file can be replaced during build by using the `fileReplacements` array.
// `ng build ---prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.
var environment = {
    production: false
};
/*
 * In development mode, to ignore zone related error stack frames such as
 * `zone.run`, `zoneDelegate.invokeTask` for easier debugging, you can
 * import the following file, but please comment it out in production mode
 * because it will have performance impact when throw error
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.


/***/ }),

/***/ "./src/main.ts":
/*!*********************!*\
  !*** ./src/main.ts ***!
  \*********************/
/*! no exports provided */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_platform_browser_dynamic__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/platform-browser-dynamic */ "./node_modules/@angular/platform-browser-dynamic/fesm5/platform-browser-dynamic.js");
/* harmony import */ var _app_app_module__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./app/app.module */ "./src/app/app.module.ts");
/* harmony import */ var _environments_environment__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./environments/environment */ "./src/environments/environment.ts");




if (_environments_environment__WEBPACK_IMPORTED_MODULE_3__["environment"].production) {
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["enableProdMode"])();
}
Object(_angular_platform_browser_dynamic__WEBPACK_IMPORTED_MODULE_1__["platformBrowserDynamic"])().bootstrapModule(_app_app_module__WEBPACK_IMPORTED_MODULE_2__["AppModule"])
    .catch(function (err) { return console.log(err); });


/***/ }),

/***/ 0:
/*!***************************!*\
  !*** multi ./src/main.ts ***!
  \***************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__(/*! C:\DEV\angularProjects\finalProjectFrontEnd\src\main.ts */"./src/main.ts");


/***/ })

},[[0,"runtime","vendor"]]]);
//# sourceMappingURL=main.js.map