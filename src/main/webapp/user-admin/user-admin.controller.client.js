(function () {
  let users = [];

  const onclickEventHandler = () => {
    alert("heading clicked");
  };

  const deleteUser = (index) => {
    const user = users[index];
    const userId = user._id;
    userService.deleteUser(userId).then((status) => {
      // console.log(status)
      users.splice(index, 1);
      renderUsers(users);
    });
  };

  let selectedUserIndex = -1;
  const selectUser = (index) => {
    selectedUserIndex = index;
    $("#usernameFld").val(users[index].username);
    $("#firstNameFld").val(users[index].first);
    $("#lastNameFld").val(users[index].last);
    $("#roleFld").val(users[index].role);
  };

  let $template;
  let tbody;

  const renderUser = (user) => {
    const username = user.username;
    const fName = user.first;
    const lName = user.last;
    const role = user.role;
    const userId = user._id;

    const $clone = $template.clone();

    $clone.removeClass("wbdv-hidden");

    const $username = $clone.find(".wbdv-username");
    $username.html(username);
    const $firstName = $clone.find(".wbdv-first-name");
    $firstName.html(fName);
    const $lastName = $clone.find(".wbdv-last-name");
    $lastName.html(lName);
    const $role = $clone.find(".wbdv-role");
    $role.html(role);
    const userIdx = users.findIndex((u) => u._id === userId);
    const $removeBtn = $clone.find(".wbdv-remove");
    $removeBtn.click(() => deleteUser(userIdx));
    $clone.find(".wbdv-select").click(() => selectUser(userIdx));
    tbody.append($clone);
  };

  function renderUsers(users) {
    tbody.empty();
    for (let i = 0; i < users.length; i++) {
      const user = users[i];
      renderUser(user);
    }
  }

  const createUser = () => {
    const username = $("#usernameFld").val();
    const first = $("#firstNameFld").val();
    const last = $("#lastNameFld").val();
    const role = $("#roleFld").val();
    const password = $("#passwordFld").val()
    const newUser = {
      username,
      first,
      last,
      role,
      password,
    };
    userService.createUser(newUser).then((actualInsertedUser) => {
      // users.push(actualInsertedUser)
      findAllUsers();
      // renderUsers(users)
    });
    clearForms();
  };

  const clearForms = () => {
    $("#usernameFld").val("");
    $("#firstNameFld").val("");
    $("#lastNameFld").val("");
    $("#roleFld").val("");
    $("#passwordFld").val("");
  };

  const updateUser = () => {
    const updatedFields = {
      username: $("#usernameFld").val(),
      first: $("#firstNameFld").val(),
      last: $("#lastNameFld").val(),
      role: $("#roleFld").val(),
      password: $("passwordFld").val(),
    };
    const userId = users[selectedUserIndex]._id;
    userService.updateUser(userId, updatedFields).then((status) => {
      findAllUsers();
      // users[selectedUserIndex] = updatedFields;
      // renderUsers(users);
    });
    clearForms();
  };

  const findAllUsers = () => {
    userService.findAllUsers().then((_users) => {
      console.log(_users);
      users = _users;
      renderUsers(users);
    });
  };

  const findUserById = (userId) => {
    userService.findUserById(userId).then(_user => {
      console.log(_user);
    });
  };

  const searchUser = () => {
    const searchFields = {
      username: $("#usernameFld").val(),
      first: $("#firstNameFld").val(),
      last: $("#lastNameFld").val(),
      role: $("#roleFld").val(),
    };

    if(!searchFields.username && !searchFields.first && !searchFields.last && !searchFields.role){
      findAllUsers();
      return;
    }

    const filteredUsers = users.filter((user) => {
      if (
        user.username.includes(searchFields.username) &&
        user.first.includes(searchFields.first) &&
        user.last.includes(searchFields.last) &&
        user.role.includes(searchFields.role)
      ) {
        return user;
      }
    });

    renderUsers(filteredUsers);
  };

  function main() {
    const heading1 = jQuery("h1");
    // heading1.remove()
    heading1
      // .css("backgroundColor", "blue")
      .html("User Administration")
      // .append(" - for Administrators Only")
      .click(onclickEventHandler);

    $template = jQuery(".wbdv-template");
    tbody = $("tbody.wbdv-tbody");
    $(".wbdv-create").click(createUser);
    $(".wbdv-update").click(updateUser);
    $(".wbdv-search").click(searchUser);

    // userService.findAllUsers()
    //   .then(_users => {
    //     console.log(_users)
    //     users = _users
    //     renderUsers(users)
    //   })

    findAllUsers();
    // renderUsers(users)
  }

  jQuery(main);
})();
