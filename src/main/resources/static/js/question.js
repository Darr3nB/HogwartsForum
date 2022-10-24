function questionHtml(){
    function initEventListener(){
        document.querySelector("#post-question-button").addEventListener('click', postQuestion);
    }

    function postQuestion(event){
        event.preventDefault()
        const questionTitle = document.querySelector("#question-title").value;
        const questionDescription = document.querySelector("#question-description").value;

        console.log(questionTitle, questionDescription);
    }

    initEventListener();
}

questionHtml();
