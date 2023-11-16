It looks like you have a combination of code snippets and directives related to a Java project, specifically involving authentication, authorization, and formatting in a Spring application. Additionally, you've mentioned some Thymeleaf expressions for formatting currency and date/time.

Let's break down your README file to include relevant information:

---

# Project Title (DuAnTotNghiep)

## Overview
Briefly describe the purpose and scope of the project.

## Authentication and Authorization

### Using `@PreAuthorize`
In your Java code, you are using the `@PreAuthorize` annotation to restrict access to a method with the 'ADMIN' authority. Ensure that only users with the 'ADMIN' authority can execute the annotated method.

Example:
```java
@PreAuthorize("hasAuthority('ADMIN')")
public void adminOnlyMethod() {
    // Your code here
}
```

## Formatting

### Currency Formatting
You are using Thymeleaf expressions to format a currency value. The expression includes replacing trailing '.00' and appending ' VNĐ'.

Example:
```html
th:text="${#strings.replace(#numbers.formatDecimal(voucher?.discount, 0, 'COMMA', 2, 'POINT'), '.00', '')} + ' VNĐ'"
```

### Date/Time Formatting
You are using Thymeleaf to format a date/time value in the 'dd-MM-yyyy HH:mm' format.

Example:
```html
td th:text="${#temporals.format(voucher?.start_time, 'dd-MM-yyyy HH:mm')}"
```

## How to Use
Provide instructions on how to clone, set up, and run the project. Include any dependencies or configurations needed for successful execution.

## Additional Notes
Any other relevant information, tips, or special considerations can be added here.

---

This is a basic structure, and you should tailor it to fit the specifics of your project. Include more details about your project, dependencies, and any special considerations that users might need to know.
