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
  if (element && element.parentNode) {
    const errormessage = parentnote.querySelector(".error");
    if (errormessage) {
      errormessage.remove();
    }
  }
}
