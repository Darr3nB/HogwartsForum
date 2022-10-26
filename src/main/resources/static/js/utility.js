export let utility = {
    apiGetReturningJson: async function (path) {
        return await fetch(`/api/${path}`).then(response => response.json());
    },

    apiGetReturningStatus: async function (path) {
        await fetch(`/api/${path}`).then(response => {
            return response.status;
        });
    },

    apiPostReturningStatus: async function (path, dataAsDict) {
        const login = await fetch(`/api/${path}`, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(dataAsDict)
        })
            .then(response => {
                return response.status;
            })
            .catch(reason => console.log(`Error happened: ${reason}`));
    },

    apiPostReturningJson: async function (path, dataAsDict) {
        const login = await fetch(`/api/${path}`, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(dataAsDict)
        })
            .then(response => {
                return response.json();
            })
            .catch(reason => console.log(`Error happened: ${reason}`));
    },
}