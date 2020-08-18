import React from 'react';
import { Container, Row, Col } from 'react-bootstrap';
import InputConTainer from './InputContainer'
import ButtonContainer from './ButtonContainer';
import { useState } from 'react';
import config from '../../../asset/config.json'
import { connect } from 'react-redux';
import * as actions from '../../../actions/index'
import { useHistory } from "react-router-dom";
import { useParams } from 'react-router-dom';

function Form(props) {
    const params = useParams();
    const [ID, setID] = useState(params.id);
    const [listBook, setListBook] = useState("");
    const history = useHistory();

    function createRentingBill() {
        console.log("userLogin", props.userLogin);
        let url = config.severAPi.hostUrl + ":8081/book/create_renting_slip";
        let dataArr = listBook.split(",");
        for (let i = 0; i < dataArr.length; i++) {
            if (isNaN(dataArr[i]) || dataArr[i] == "") {
                alert("Dữ liệu nhập vào không hợp lệ");
                return;
            }
            dataArr[i] = parseInt(dataArr[i]);
        }

        let body = {
            accountID: ID,
            bookID: dataArr
        }

        fetch(url, {
            method: "post",
            headers: { "Content-Type": "application/json", "x-access-token": localStorage.quanlythuvien_accesstoken },
            body: JSON.stringify(body)
        })
            .then(res => res.json())
            .then(data => {
                console.log(data);
                if(data.result){
                    alert(data.message);
                }
                else{
                    alert(data.message);
                }
                
            });
    }

    return (
        <Container>
            <InputConTainer label="ID Người mượn:" byName={setID} value={ID} />
            <InputConTainer label="List ID sách mượn:" byName={setListBook} />
            <ButtonContainer byEventCreate={createRentingBill} />
        </Container>
    );
}

const mapStateToProps = state => {
    return {
        systems: state.systems
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