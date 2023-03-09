"use strict";
import {byId} from "./util.js";

var sessies = JSON.parse(sessionStorage.getItem("interessanteSessies"));
var list = byId("mijnInteresses");
for (const sessie of sessies) {
    var li = document.createElement("li");
    li.innerText = sessie.naam;
    list.appendChild(li);
}