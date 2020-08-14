import React from 'react';
import { Container, Row, Col } from 'react-bootstrap';
import InputConTainer from './InputContainer'
import ButtonContainer from './ButtonContainer';
import { useState } from 'react';
import config from '../../../asset/config.json'

function Form(props) {
    const [name, setName] = useState("");

    function setInputName(data) {
        setName(data);
    }

    function createAuthor() {
        let url = config.severAPi.hostUrl + ":8081/book/add_author";
        fetch(url, {
            method: "post",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({name: name, currentUserID: 1})
        })
            .then(res => res.json())
            .then(data => {
                if(data.result){
                    alert(data.message);
                }
            });
    }

    return (
        <Container>
            <InputConTainer label="Tên:" byName={setInputName} />
            <ButtonContainer byEventCreate={createAuthor} />
        </Container>
    );
}

export default Form;