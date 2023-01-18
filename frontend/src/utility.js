import {useNavigate} from "react-router-dom";

export let utility = {
    apiPostWithDictionaryDataType: async function (path, dataAsDict) {
        return await fetch(path, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(dataAsDict)
        })
            .catch(reason => console.log(`An error happened: ${reason}`));
    },

    apiGet: async function (path) {
        return await fetch(path);
    },
    isLoggedInRequest: async function () {
        return await utility.apiGet(`/user/logged-in`)
            .then(r => {
                if (r.status === 204) {
                    return false;
                } else if (r.status === 200) {
                    return r.json();
                }
            });
    },
    apiDeleteWithPathData: async function (path){
        return await fetch(path, {
            method: "DELETE"
        }).catch(reason => console.log(`An error happened: ${reason}`));
    }
}