let baseUrl = "http://localhost:8080"

document.getElementById("createRequest").addEventListener("click", redirect, false);
const welcome = document.getElementById("welcome-message");
const reimbursement = document.getElementById("reimbursement");
const topRow = document.getElementById("table-top-row");
const typeOfEmployee = window.sessionStorage.getItem('type');

async function getRequests(){
    welcome.innerText = `Welcome ${window.sessionStorage.getItem('username')}`
    
    if(typeOfEmployee == "EMPLOYEE"){
        reimbursement.innerText = `Remaining Reimbursement: ${window.sessionStorage.getItem('reimbursement')}`
    } else{
        let newCol = document.createElement('th');
        newCol.innerText = "Approve Request"
        topRow.appendChild(newCol);
    }

    let searchId = JSON.stringify(window.sessionStorage.getItem('id'));

    let res = await fetch(
        `${baseUrl}/account/request`,
        {
            method: 'GET',
            header: {'Content-Type': 'application/json'}, 
        }
    );

    let resJSON = res.json()
    .then((resp) => {
        for(let i = 0; i < resp.length; i++){
            let newRow = document.createElement('tr');
            let col1 = document.createElement('td');
            col1.innerText = resp[i].id;
            newRow.appendChild(col1);
            let col2 = document.createElement('td');
            console.log(resp[i].denied);
            col2.innerText = resp[i].approved ? 'Approved': resp[i].denied ? 'Denied': 'pending';
            newRow.appendChild(col2);
            let col3 = document.createElement('td');
            col3.innerText = resp[i].amount;
            newRow.appendChild(col3);
            console.log(typeOfEmployee)
            if(typeOfEmployee != "EMPLOYEE" && !resp[i].denied && !resp[i].approved){
                let col4 = document.createElement('td');
                let approveButton = document.createElement('button');
                let denyButton = document.createElement('button');
                approveButton.style.color = "white"
                approveButton.innerText = "Approve"
                denyButton.style.color = "white"
                denyButton.innerText = "Deny"
                approveButton.addEventListener('click', approve, false);
                denyButton.addEventListener('click', deny, false);
                col4.appendChild(approveButton);
                col4.appendChild(denyButton);
                newRow.appendChild(col4);
            }
            document.getElementById('requests').appendChild(newRow);
        }
        console.log(resp);
    })
}

function redirect(){
    window.location.assign("requestform.html");
}

async function approve(event){
    let requestJson = {
        id: event.target.parentElement.parentElement.childNodes[0].innerText
    }

    let request = JSON.stringify(requestJson);

    let res = await fetch(
        `${baseUrl}/account/request/approve`,
        {
            method: 'PATCH',
            header: {'Content-Type': 'application/json'},
            body: request
        }
    )

    let resJSON = await res.json()
    .then((resp) => {
        //location.reload();
    })
    .catch((err) => {
        console.log(err);
    })
}

async function deny(event){
    let requestJson = {
        id: event.target.parentElement.parentElement.childNodes[0].innerText
    }

    let request = JSON.stringify(requestJson);

    let res = await fetch(
        `${baseUrl}/account/request/deny`,
        {
            method: 'PATCH',
            header: {'Content-Type': 'application/json'},
            body: request
        }
    )

    let resJSON = await res.json()
    .then((resp) => {
        //location.reload();
    })
    .catch((err) => {
        console.log(err);
    })
}