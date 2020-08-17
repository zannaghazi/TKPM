import React from 'react';
import { Container, Row, Col } from 'react-bootstrap';
import InputConTainer from './InputContainer'
import ButtonContainer from './ButtonContainer';
import { useState } from 'react';
import config from '../../../asset/config.json'
import { useHistory } from "react-router-dom";

function Form(props) {
    const [ISBN, setISBN] = useState("");
    const [amount, setAmount] = useState(0);
    const history = useHistory();

    function createBook() {
        console.log(ISBN, amount);
        let url = config.severAPi.hostUrl + ":8081/book/add_book";
        let data = {
            ISBN: ISBN,
            amount: amount
        }
        fetch(url, {
            method: "post",
            headers: { "Content-Type": "application/json", "x-access-token": localStorage.quanlythuvien_accesstoken },
            body: JSON.stringify(data)
        })
            .then(res => res.json())
            .then(data => {
                console.log(data);
                alert("Thêm sách mơi thành công");
                history.push('/dashboard/book_management');
            });
    }

    return (
        <Container>
            <InputConTainer label="ISBN:" byData={setISBN}/>
            <InputConTainer label="Số lượng:" byData={setAmount}/>
            <ButtonContainer byEventCreate={createBook} />
        </Container>
    );
}

export default Form;