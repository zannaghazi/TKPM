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

    function createPublisher() {
        console.log("userLogin",props.userLogin);
        let url = config.severAPi.hostUrl + ":8081/book/add_publisher";
        fetch(url, {
            method: "post",
            headers: { "Content-Type": "application/json", "x-access-token": localStorage.quanlythuvien_accesstoken },
            body: JSON.stringify({name: name, currentUserID: props.userLogin.id})
        })
            .then(res => res.json())
            .then(data => {
                console.log(data);
                props.onAddNewPublisher(data.data);
                alert("Thêm nhà xuất bản mới thành công");
                history.push('/dashboard/publish_management');
            });
    }

    return (
        <Container>
            <InputConTainer label="Tên:" byName={setInputName} />
            <ButtonContainer byEventCreate={createPublisher} />
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
		onAddNewPublisher: (pulisher) => {
            dispatch(actions.addNewPublisher(pulisher));
        }
	}
}

export default connect(mapStateToProps, mapDispatchToProps)(Form);