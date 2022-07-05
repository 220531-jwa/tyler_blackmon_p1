let baseUrl = "http://localhost:8080";
document.getElementById("login").addEventListener("click",login, false);

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
        window.sessionStorage.clear();
        if(res.status == 200){
            window.sessionStorage.setItem('username', resp.username);
            window.sessionStorage.setItem('id', resp.id);
            window.sessionStorage.setItem('reimbursement', resp.availableReimbursement);
            window.sessionStorage.setItem('type', resp.type)
            window.location.assign("homePage.html");
        }else{
            alert("Invalid username or password");
        }
    })
    .catch((err) => {
        console.log(err);
    })
}
