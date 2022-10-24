function questionHtml() {
    function initEventListener() {
        document.querySelector("#post-question-button").addEventListener('click', postQuestion);
    }

    function postQuestion(event) {
        event.preventDefault()
        const questionTitle = document.querySelector("#question-title").value;
        const questionDescription = document.querySelector("#question-description").value;

        sendQuestionToBackend(questionTitle, questionDescription);
    }

    function sendQuestionToBackend(questionTitle, questionDescription) {
        const result = fetch("/post-question", {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({'title': questionTitle, 'questionText': questionDescription})
        })
            .then(response => {
                if (response.status === 403) {
                    alert("Title or description is too short!");
                } else if (response.ok) {
                    window.location.href = "/";
                }
            })
            .catch(reason => console.log(`An error happened: ${reason}`));
    }

    initEventListener();
}

questionHtml();
