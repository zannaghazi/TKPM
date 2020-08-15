import React from 'react';
import { Container, Row, Col } from 'react-bootstrap';
import InputConTainer from './InputContainer'
import ButtonContainer from './ButtonContainer';
import { useState } from 'react';
import config from '../../../asset/config.json'
import { connect } from 'react-redux';
import * as actions from '../../../actions/index'
import { useHistory } from "react-router-dom";

function Form(props) {
    const [name, setName] = useState("");
    const history = useHistory();

    function setInputName(data) {
        setName(data);
    }

    function createAuthor() {
        console.log("userLogin",props.userLogin);
        let url = config.severAPi.hostUrl + ":8081/book/add_author";
        fetch(url, {
            method: "post",
            headers: { "Content-Type": "application/json", "x-access-token": localStorage.quanlythuvien_accesstoken },
            body: JSON.stringify({name: name, currentUserID: props.userLogin.id})
        })
            .then(res => res.json())
            .then(data => {
                console.log(data);
                props.onAddNewAuthor(data.data);
                alert("Thêm tác giả mơi thành công");
                history.push('/dashboard/author_management');
            });
    }

    return (
        <Container>
            <InputConTainer label="Tên:" byName={setInputName} />
            <ButtonContainer byEventCreate={createAuthor} />
        </Container>
    );
}

const mapStateToProps = state => {
	return {
		userLogin: state.user_login
	}
}

const mapDispatchToProps = (dispatch, props) => {
	return {
		onAddNewAuthor: (author) => {
            dispatch(actions.addNewAuthor(author));
        }
	}
}

export default connect(mapStateToProps, mapDispatchToProps)(Form);