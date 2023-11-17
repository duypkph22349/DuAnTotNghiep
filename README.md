Certainly! It looks like you have code snippets related to formatting currency, date and time, order types, and status for a billing system. Below is a simplified version of the README file with explanations for each section:

```markdown
# Project README

## Formatting Currency

To format currency in the application, the following Thymeleaf expression is used:

```html
th:text="${#strings.replace(#numbers.formatDecimal(voucher?.discount, 0, 'COMMA', 2, 'POINT'), '.00', '')} + ' VNĐ'"
```

This expression utilizes Thymeleaf's string manipulation and number formatting functions to display the voucher discount in Vietnamese Dong (VNĐ), removing any trailing '.00'.

## Formatting Date and Time

For formatting date and time, the following Thymeleaf expression is employed:

```html
td th:text="${#temporals.format(voucher?.start_time, 'dd-MM-yyyy HH:mm')}"
```

This expression uses Thymeleaf's temporals formatting to display the start time of a voucher in the 'dd-MM-yyyy HH:mm' format.

## Order Types

In the billing system, orders can have different types, identified by the following codes:

- 0: Tại quầy (At the counter)
- 1: Online

These codes represent the order placement method.

## Status for All Tables

In the application, status codes are used universally to denote the active or inactive state. The status codes are as follows:

- 0: Ngừng kích hoạt (Deactivated)
- 1: Kích hoạt (Activated)

These codes are applied across all tables in the system.

## Bill Status Timeline

The timeline of a bill's status is represented by the following codes:

- 0: Chờ xác nhận (Pending confirmation)
- 1: Xác nhận thành công (Confirmed successfully)
- 2: Chờ giao hàng (Pending delivery)
- 3: Đang giao hàng (In transit)
- 4: Đã giao hàng (Delivered)
- 5: Thành công (Successful)

These codes reflect the different stages a bill goes through, from confirmation to successful delivery.

Feel free to update this README file as needed to provide more context or details specific to your project.
```
