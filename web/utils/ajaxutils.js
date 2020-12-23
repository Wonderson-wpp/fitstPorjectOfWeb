function ajax(
    /*method,
    url,
    asyn,
    param,
    callback,
    type*/
    option
) {
    var text;
    if (option.method == null) {
        option.method = "get";
    }
    if (option.url == null) {
        throw new Error();
    }
    if (option.asyn == undefined) {
        option.asyn = true;
    }
    var httpRequest = new XMLHttpRequest();
    // alert(option.method + ", " + option.url +", " +  option.asyn);
    httpRequest.open(option.method, option.url,option.asyn);
    if (option.method == "post" || option.method == "POST") {
        httpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        httpRequest.send(option.param);
        alert(option.param);
    } else {
        httpRequest.send(null);
    }
    httpRequest.onreadystatechange = function () {
        if (httpRequest.readyState == 4 && httpRequest.status == 200) {

            if (option.type == null) {
                text = httpRequest.responseText;
            } else if (option.type == "text") {
                text = httpRequest.responseText;
            } else if (option.type == "xml") {
                text = httpRequest.responseXML;
            } else if (option.type == "json") {
                // alert(httpRequest.responseText);
                text = JSON.parse(httpRequest.responseText);
            }
            option.callback(text);

        }

    };
    return text;

}