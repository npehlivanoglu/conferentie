"use strict";
import {byId, toon} from "./util.js";

var sessie = JSON.parse(sessionStorage.getItem("sessie"));

const response = await fetch(`sprekers/${sessie.sprekerid}`);
if (response.ok) {
    var spreker = await response.json();
    byId("naam").innerText = sessie.naam;
    byId("uur").innerText = sessie.uur;
    byId("spreker").innerText = `${spreker.voornaam} ${spreker.familienaam}, ${spreker.titel} bij ${spreker.firma}`;
    addInteressanteSessie(sessie);


    let interessanteSessies = JSON.parse(sessionStorage.getItem("interessanteSessies"));
    if (interessanteSessies !== null && interessanteSessies.some(s => s.naam === sessie.naam && s.uur === sessie.uur)) {
        byId("interessant").innerText = "Geen interesse meer";
    }

} else {
    toon("storing");
}

function addInteressanteSessie(sessie) {
    byId("interessant").onclick = function () {
        let interessanteSessies = JSON.parse(sessionStorage.getItem("interessanteSessies"));
        if (interessanteSessies === null) {
            interessanteSessies = [];
        }


        let index = interessanteSessies.findIndex(s => s.naam === sessie.naam && s.uur === sessie.uur);
        if (index !== -1) {
            interessanteSessies.splice(index, 1);
            byId("interessant").innerText = "Interessant";
        } else {
            interessanteSessies.push(sessie);
            byId("interessant").innerText = "Geen interesse meer";
        }

        sessionStorage.setItem("interessanteSessies", JSON.stringify(interessanteSessies));
    };
}
