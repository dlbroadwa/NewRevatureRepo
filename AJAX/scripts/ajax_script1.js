import {Sequence} from "./sequence.js";

const numberOfButtons = new Sequence(1);

function addButton() {
    let button = document.createElement("button");
    button.id = "my-button" + numberOfButtons.next;
    button.type = "button";
    button.textContent = "my button";
    document.getElementById("main-section").appendChild(button);
}

