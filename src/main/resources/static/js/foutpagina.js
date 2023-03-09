"use strict";
import {byId} from "./util.js";

byId("conflict").innerText = sessionStorage.getItem("conflict");