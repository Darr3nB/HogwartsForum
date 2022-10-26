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
    }
}
