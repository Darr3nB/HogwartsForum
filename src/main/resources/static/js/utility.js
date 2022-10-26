export let utility = {
    apiGet: async function (path) {
        return await fetch(path);
    },

    apiPost: async function (path, dataAsDict) {
        return await fetch(path, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(dataAsDict)
        })
            .catch(reason => console.log(`Error happened: ${reason}`));
    },

    addEventListenerToQuestions: function () {
        const allQuestion = document.querySelectorAll("td");
        allQuestion.forEach(element => {
            element.addEventListener('click', utility.clickOnSpecificQuestion);
        });
    },

    clickOnSpecificQuestion: function (event) {
        const selectedQuestionId = +event.currentTarget.id.replace("question-id-", "");

        utility.apiGet(`/post-question/specific-question/${selectedQuestionId}`).then(response => {
            window.location.href = response.url;
        });
    }
}
