"use strict";
import {byId, toon} from "./util.js";

var sessie = JSON.parse(sessionStorage.getItem("sessie"));

const response = await fetch(`sprekers/${sessie.sprekerid}`);
if (response.ok) {
    var spreker = await response.json();
    byId("naam").innerText = sessie.naam;
    var sessieUur = (sessie.uur).split(':');
    byId("uur").innerText = `${sessieUur[0]}:${sessieUur[1]}`;
    byId("spreker").innerText = `${spreker.voornaam} ${spreker.familienaam}, ${spreker.titel} bij ${spreker.firma}`;
    addInteressanteSessie(sessie);


    let interessanteSessies = JSON.parse(sessionStorage.getItem("interessanteSessies"));
    if (interessanteSessies !== null && interessanteSessies.some(s => s.id === sessie.id)) {
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


        let index = interessanteSessies.findIndex(s => s.id === sessie.id);
        if (index !== -1) {
            interessanteSessies.splice(index, 1);
            byId("interessant").innerText = "Interessant";
        } else {
            var sessieIdEnNaam = {
                id: sessie.id,
                naam: sessie.naam
            };
            interessanteSessies.push(sessieIdEnNaam);
            byId("interessant").innerText = "Geen interesse meer";
        }

        sessionStorage.setItem("interessanteSessies", JSON.stringify(interessanteSessies));
        window.location = "index.html";
    };
}
