const url = "http://localhost:8080/task/user/1";

function hideLoader() {
    document.getElementById("loading").style.display = "none";
}

function show(tasks) {
    let tab = ` 
        <thead>
            <th scope="col">ID Task</th>
            <th scope="col">Description</th>
            <th scope="col">Username</th>
            <th scope="col">User ID</th>
        </thead>
    `

    for (let task of tasks) {
        tab += `
        <tr>
            <td scope="row">${task.id}</td>
            <td scope="row">${task.description}</td>
            <td scope="row">${task.user.username}</td>
            <td scope="row">${task.user.id}</td>
        </tr>
        `
    }

    document.getElementById("tasks").innerHTML = tab;
}

async function getAPI(url) {
    try {
        const response = await fetch(url, {
            method: "GET",
        });

        const data = await response.json();

        if (response) {
            hideLoader();
            show(data);

        } else if (!response.ok) {
            throw new Error("Network response was not ok");
        }


    } catch (error) {
        console.error("There was a problem with the fetch operation:", error);
    }
}

getAPI(url);