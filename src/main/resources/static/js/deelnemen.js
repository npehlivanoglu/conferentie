"use strict";
import {byId, toon, verberg} from "./util.js";

verbergFouten();
byId("toevoegen").onclick = async function () {
    const voornaamInput = byId("voornaam");
    if (!voornaamInput.checkValidity()) {
        toon("voornaamFout");
        voornaamInput.autofocus;
        return;
    }

    const familienaamInput = byId("familienaam");
    if (!familienaamInput.checkValidity()) {
        toon("familienaamFout");
        familienaamInput.autofocus;
        return;
    }

    const emailInput = byId("email");
    if (!emailInput.checkValidity()) {
        toon("emailFout");
        emailInput.autofocus;
        return;
    }
    const deelnemer = {
        voornaam: voornaamInput.value,
        familienaam: familienaamInput.value,
        email: emailInput.value
    };
    const sessies = JSON.parse(sessionStorage.getItem("interessanteSessies"));
    const nieuweBoeking = {
        deelnemer: deelnemer,
        sessies: sessies
    };
    console.log(JSON.stringify(nieuweBoeking));
    voegBoeking(nieuweBoeking);
}


function verbergFouten() {
    verberg("voornaamFout");
    verberg("familienaamFout");
    verberg("emailFout");
    verberg("storing");
}

async function voegBoeking(boeking) {
    const response = await fetch("deelnemers", {
        method: "POST",
        headers: {'Content-Type': "application/json"},
        body: JSON.stringify(boeking)
    });
    if (response.ok) {
        const jsonResponse = await response.json();
        byId("bevestig").innerText = `Goedgekeurd. ID: ${JSON.stringify(jsonResponse)}`;
    } else {
        toon("storing");
    }
}