import {utility} from "./utility.js";

function questionHtml() {
    function initEventListener() {
        document.querySelector("#post-question-button").addEventListener('click', postQuestion);
    }

    function postQuestion(event) {
        event.preventDefault()
        const questionTitle = document.querySelector("#question-title").value;
        const questionDescription = document.querySelector("#question-description").value;

        utility.apiPostWithDictionaryDataType("/post-question", {'title': questionTitle, 'questionText': questionDescription})
            .then(response => {
                if (response.status === 403) {
                    alert("Title or description is too short!");
                } else if (response.ok) {
                    window.location.href = "/";
                }
            })
    }

    initEventListener();
}

questionHtml();
