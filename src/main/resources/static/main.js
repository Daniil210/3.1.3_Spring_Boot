// ================================================ FETCH MAIN TABLE ===================================================

// const mainTableList = document.querySelector('.main-table-list');
const mainTableList = document.querySelector('.main-table-list');
let outputAll = '';

const renderMainTable = (data) => {
    console.log("6th")
    outputAll += `
        <table class="table table-hover table-striped">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Age</th>
                    <th>Email</th>
                    <th style="display:none">Password</th>
                    <th>Role</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
                </thead>

                <tbody>
        `
    data.forEach(user => {
            console.log(user)
            outputAll += `                
                <tr data-id="${user.id}" id="row-user-${user.id}">
                    <td class="main-id" id="main-id-${user.id}">${user.id}</td>
                    <td class="main-name" id="main-name-${user.id}">${user.name}</td>
                    <td class="main-surname" id="main-surname-${user.id}">${user.surname}</td>
                    <td class="main-age" id="main-age-${user.id}">${user.age}</td>
                    <td class="main-email" id="main-email-${user.id}">${user.email}</td>
                    <td class="main-password" id="main-password-${user.id}" style="display:none">${user.password}</td>
                    <td class="main-roles" id="main-roles-${user.id}">`
            user.roles.forEach(val => {
                console.log(val)
                outputAll += `
                        ${val.role.substring(5)}
                        `
            })

            outputAll += `
                    </td>
                    <td>
                        <button type="button" class="btn btn-info" data-toggle="modal"
                                data-target="#editModal" id="edit-user"> Edit </button>
                    </td>
                    <td>
                        <button type="button" class="btn btn-danger" data-toggle="modal"
                                data-target="#deleteModal" id="delete-user"> Delete </button>
                    </td>
                </tr>                
                `;
        }

    );
    outputAll += `
        </tbody>

        </table>
        `

    //document.getElementById('main-table-list').innerHTML = outputAll;
    mainTableList.innerHTML = outputAll;
}


fetch("api/users")
    .then(res => res.json())
    .then(data => renderMainTable(data))

// ================================================ FETCH MAIN TABLE ===================================================


// ================================================ MODAL EDIT/DELETE TABLE ============================================

// const mainTableList = document.querySelector('.main-table-list');
const modalEdit = document.querySelector('.modal-edit-form');
const modalDelete = document.querySelector('.modal-delete-form');
const modalBtnEditSubmit = document.getElementById('edit-inside-modal')
const modalBtnDeleteSubmit = document.getElementById('delete-inside-modal')

const rolesEdit = $('#rolesEdit');

let deleteId
let editId

mainTableList.addEventListener('click', (e) => {
    e.preventDefault();

    let editButtonIsPressed = e.target.id === 'edit-user'
    let deleteButtonIsPressed = e.target.id === 'delete-user'

    const parent = e.target.parentElement.parentElement
    // let userId = e.target.parentElement.dataset.id

    // let editId = parent.querySelector('.main-id').textContent
    let editName = parent.querySelector('.main-name').textContent
    let editSurname = parent.querySelector('.main-surname').textContent
    let editAge = parent.querySelector('.main-age').textContent
    let editEmail = parent.querySelector('.main-email').textContent
    let editPassword = parent.querySelector('.main-password').textContent
    let editRoles = parent.querySelector('.main-roles').textContent

    deleteId = e.target.parentElement.parentElement.dataset.id

    editId = e.target.parentElement.parentElement.dataset.id

    let insideEditId = parent.querySelector('.main-id').textContent
    let insideDeleteId = parent.querySelector('.main-id').textContent


    if(editButtonIsPressed) {

        console.log('============================')
        console.log('edit_user_id='+editId)
        console.log('============================')
        // let insideEditId = editId
        console.log('edit_user_id_UPD='+insideEditId)
        console.log('============================')


        document.getElementById('idEdit').value = editId;
        document.getElementById('nameEdit').value = editName;
        document.getElementById('surnameEdit').value = editSurname;
        document.getElementById('ageEdit').value = editAge;
        document.getElementById('emailEdit').value = editEmail;
        document.getElementById('passwordEdit').value = editPassword;
        document.getElementById('rolesEdit').value = editRoles;

        console.log('editRoles = ', editRoles)
        console.log('editRoles trim = ', editRoles.trim())
        console.log("typeof editRoles", typeof editRoles)

        console.log('split = ', editRoles.split(' '))
        console.log('clean = ', editRoles.replace(/ /g,''))

        const select = document.getElementById('rolesEdit').getElementsByTagName('option');

        let rolesForUser = 0;

        //const arrayOfRoles = editRoles.replace(/\r?\n|\r/g, ' ').trim().split(' ');
        const arrayOfRoles = editRoles.split(' ');

        for(let i of arrayOfRoles) {
            if (i.includes('ADMIN')) {
                select[0].selected = true;
            } else if (i.includes('USER')) {
                select[1].selected = true;
            }
        }
        console.log("arrayOfRoles - ", arrayOfRoles)
        console.log("rolesForUser = ", rolesForUser)
    }


    if(deleteButtonIsPressed) {

        console.log('============================')
        console.log('delete_user_id='+deleteId)
        console.log('============================')

        document.getElementById('idDelete').value = deleteId;
        document.getElementById('nameDelete').value = editName;
        document.getElementById('surnameDelete').value = editSurname;
        document.getElementById('ageDelete').value = editAge;
        document.getElementById('emailDelete').value = editEmail;
        document.getElementById('rolesDelete').value = editRoles;

    }

    modalEdit.addEventListener('click', watchModalEdit)

    modalDelete.addEventListener('click', watchModalDelete)

})

function watchModalEdit(e) {

    let insideEditId = editId;

    const valuesEdit = rolesEdit.val();

    console.log('inside modal E')
    console.log('editId = '+editId)
    console.log('insideEditId = '+insideEditId)
    console.log('------------------------------')
    e.preventDefault();

    let editModalButtonIsPressed = e.target.id === 'edit-inside-modal'

    if(editModalButtonIsPressed) {
        console.log('EEE')
        console.log('editId = '+editId)
        console.log('insideEditId = '+insideEditId)
        console.log('------------------------------')



        fetch(`/api/users`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json; charset=utf-8'
            },
            body: JSON.stringify({
                id: document.getElementById('idEdit').value,
                name: document.getElementById('nameEdit').value,
                surname: document.getElementById('surnameEdit').value,
                age: document.getElementById('ageEdit').value,
                email: document.getElementById('emailEdit').value,
                password: document.getElementById('passwordEdit').value,
                roles: valuesEdit
            })
        })
            .then(() => {
                mainTableList.innerHTML = "";
                outputAll = "";

                fetch("api/users")
                    .then(res => res.json())
                    .then(data => {
                        console.log(data)
                        return renderMainTable(data)
                    });
            })

    }
// ------------------- modalEditListener end -------------------
}

function watchModalDelete(e) {

    let insideDeleteId = deleteId;

    console.log('inside modal D')
    console.log('deleteId = '+deleteId)
    console.log('insideDeleteId = '+insideDeleteId)
    console.log('------------------------------')
    e.preventDefault();

    let deleteModalButtonIsPressed = e.target.id === 'delete-inside-modal'

    if(deleteModalButtonIsPressed) {
        console.log('DDD')
        console.log('deleteId = '+deleteId)
        console.log('insideDeleteId = '+insideDeleteId)
        console.log('------------------------------')


        fetch(`/api/users/${insideDeleteId}`, {
            method: 'DELETE'
        })
            .then(() => {
                document.getElementById('row-user-'+insideDeleteId).innerHTML = "";
            })

    }

// ------------------- modalDeleteListener end -------------------
}
// ================================================ MODAL EDIT/DELETE TABLE ============================================

// ================================================ ADD USER TABLE =====================================================

const addUserForm = document.querySelector('.add-user-form');
const nameNew = document.getElementById('nameNew');
const surnameNew = document.getElementById('surnameNew');
const ageNew = document.getElementById('ageNew');
const emailNew = document.getElementById('emailNew');
const passwordNew = document.getElementById('passwordNew');
const rolesNew = $('#rolesNew');


console.log('1---------------------------------------------------------------------------------')
console.log(rolesNew.val())

document.getElementById('profile-tab').addEventListener('click', (e) => {
    e.preventDefault();

    console.log('clear form - OK!')

    document.getElementById('nameNew').value = "";
    document.getElementById('surnameNew').value = "";
    document.getElementById('ageNew').value = "";
    document.getElementById('emailNew').value = "";
    document.getElementById('passwordNew').value = "";
    document.getElementById('passwordNew').value = "";
    const selectNew = document.getElementById('rolesNew').getElementsByTagName('option');
    selectNew[0].selected = false;
    selectNew[1].selected = false;

})
addUserForm.addEventListener('submit', (e) => {
    e.preventDefault();


    const values = rolesNew.val();


    fetch("api/users", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json; charset=utf-8'
        },
        // body: formData
        body: JSON.stringify({
            name: nameNew.value,
            surname: surnameNew.value,
            age: ageNew.value,
            email: emailNew.value,
            password: passwordNew.value,
            roles: values
        })
    })
        .then(() => {
            mainTableList.innerHTML = "";
            outputAll = "";

            fetch("api/users")
                .then(res => res.json())
                .then(data => {
                    console.log(data)
                    return renderMainTable(data)
                });
        })

    document.getElementById('home-tab').click();

})
// ================================================ ADD USER TABLE =====================================================