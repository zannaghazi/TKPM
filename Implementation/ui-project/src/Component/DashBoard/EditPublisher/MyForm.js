import React from 'react';
import { Container, Row, Col } from 'react-bootstrap';
import InputConTainer from './InputContainer'
import ButtonContainer from './ButtonContainer';
import { useState, useEffect } from 'react';
import config from '../../../asset/config.json'
import { connect } from 'react-redux';
import * as actions from '../../../actions/index'
import { useHistory } from "react-router-dom";
import { useParams } from 'react-router-dom';

function Form(props) {
    const [name, setName] = useState("");
    const [publisher, setPublisher] = useState({});
    const history = useHistory();

    const params = useParams();
    useEffect(() => {
        if (props.list.length > 0) {
            console.log(props.list);
            let publisherSelect = props.list.find(item => item.id == params.id);
            console.log("publisher edit", publisherSelect);
            setPublisher(publisherSelect);
            setName(publisherSelect.name);
        }
    }, [props.list])

    function setInputName(data) {
        setName(data);
    }

    function editAuthor() {
        console.log("userLogin", props.userLogin);
        // let url = config.severAPi.hostUrl + ":8081/book/update_author";
        // fetch(url, {
        //     method: "post",
        //     headers: { "Content-Type": "application/json", "x-access-token": localStorage.quanlythuvien_accesstoken },
        //     body: JSON.stringify({id: author.id, name: name, currentUserID: props.userLogin.id})
        // })
        //     .then(res => res.json())
        //     .then(data => {
        //         console.log(data);
        //         props.onEditAuthor(data.data);
        //         alert("Cập nhật tác giả mơi thành công");
        //         history.push('/dashboard/author_management');
        //     });
    }

    return (
        <Container>
            <InputConTainer label="Tên:" byName={setInputName} nameBinding={name} />
            <ButtonContainer byEventEdit={editAuthor} />
        </Container>
    );
}

const mapStateToProps = state => {
    console.log("state", state);
    return {
        userLogin: state.user_login,
        list: state.publishers
    }
}

const mapDispatchToProps = (dispatch, props) => {
    return {
        onEditAuthor: (author) => {
            dispatch(actions.editAuthor(author));
        }
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(Form);