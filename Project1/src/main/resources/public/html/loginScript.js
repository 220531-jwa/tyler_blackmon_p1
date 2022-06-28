let baseUrl = "http://localhost:8080";

async function login(){
    let uname = document.getElementById('uname').value;
    let pass = document.getElementById('pass').value;

    let employee = {
        username: uname,
        password: pass
    }

    let employeeJSON = JSON.stringify(employee);

    let res = await fetch(
        `${baseUrl}/account`,
        {
            method: 'POST',
            header: {'Content-Type': 'application/json'},
            body: employeeJSON
        }
    );

    let resJSON = await res.json()
    .then((resp) => {
        console.log(resp);
        window.location.assign("homePage.html");
    })
    .catch((err) => {
        console.log(err);
    })
}
