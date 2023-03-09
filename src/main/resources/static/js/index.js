"use strict";
import {byId, toon, verberg, verwijderChildElementenVan} from "./util.js";

setBeschikbaarTickets();
setDatums();

async function setDatums() {
    const response = await fetch("dagen");
    if (response.ok) {
        const datums = await response.json();
        const datumsList = byId("datums");
        for (const datum of datums) {
            var li = document.createElement("li");
            var hyperlink = document.createElement("a");
            hyperlink.innerText = datum.datum;
            hyperlink.href = "#";
            li.appendChild(hyperlink);
            datumsList.appendChild(li);
            hyperlink.onclick = async function () {
                const response = await fetch(`dagen/${datum.id}/sessies`);
                if (response.ok) {
                    var sessies = await response.json();
                    byId("datum").innerText = datum.datum;
                    var sessiesList = byId("sessies");
                    verwijderChildElementenVan(sessiesList);
                    for (const sessie of sessies) {
                        var li = document.createElement("li");
                        var hyperLink = document.createElement("a");
                        hyperLink.href = "sessie.html";
                        hyperLink.innerText = `${sessie.uur} ${sessie.naam}`;
                        li.appendChild(hyperLink);
                        sessiesList.appendChild(li);
                        hyperLink.onclick = function () {
                            sessionStorage.setItem("sessie", JSON.stringify(sessie));
                        };

                    }
                } else {
                    toon("storing");
                }
            }
        }
    } else {
        toon("storing");
    }
}

async function setBeschikbaarTickets() {
    const response = await fetch("tickets");
    if (response.ok) {
        var beschikbaartickets = await response.json();
        if (beschikbaartickets === 0) {
            verberg("beschikbaarTickets");
        }
        byId("beschikbaarTickets").innerText = `${beschikbaartickets} ticket(s) beschikbaar.`
    } else {
        toon("storing");
    }
}