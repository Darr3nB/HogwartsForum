export let utility = {
    apiPostWithDictionaryDataType: async function (path, dataAsDict){
        return await fetch(path, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(dataAsDict)
        })
            .catch(reason => console.log(`An error happened: ${reason}`));
    }
}