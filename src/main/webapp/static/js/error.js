let displayError = function (jqXHR) {
    let responseContentType = jqXHR.getResponseHeader("content-type")
    if (responseContentType === "text/html;charset=UTF-8") {
        let newDoc = document.open("text/html", "replace");
        newDoc.write(jqXHR.responseText);
        newDoc.close();
    } else if (responseContentType === "application/json;charset=UTF-8") {
        let responseBody = jqXHR.responseJSON
        alert("Server responded with error: " + responseBody.error)
    } else {
        console.log(jqXHR);
    }

}