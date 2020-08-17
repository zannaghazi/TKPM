import React from 'react';
import { Container, Row, Col } from 'react-bootstrap';
import InputConTainer from './InputContainer'
import DatePickerContainer from './DatePickerContainer'
import SelectedBoxContainer from './SelectedBoxContainer'
import ButtonContainer from './ButtonContainer';
import FileInputContainer from './FileInputContainer'
import { connect } from 'react-redux';
import { useEffect, useState } from 'react';
import config from '../../../asset/config.json'

const defaultValue = {
    ISBN: "",
    name: "",
    author: -1,
    publisher: -1,
    releaseDate: (new Date()).getDate() + "-" + ((new Date()).getMonth() + 1) + "-" + (new Date()).getFullYear(),
    type: -1,
    location: "",
    amount: 0,
    image: ""
};

function Form(props) {
    const [authors, setAuthors] = useState([]);
    const [typeBooks, setTypeBooks] = useState([]);
    const [publishers, setPublishers] = useState([]);
    const [data, setData] = useState(defaultValue);
    const [image, setImage] = useState(null);

    useEffect(() => {
        console.log("props in new book title", props);
        setAuthors(props.authors);
        setTypeBooks(props.type_books);
        setPublishers(props.publishers)
    }, [props.authors, props.type_books, props.publishers]);

    function changeName(dataTransfer) {
        let temp = data;
        console.log(temp);
        temp.name = dataTransfer;
        console.log(temp);
        setData(temp);
    }

    function changeISBN(dataTransfer) {
        let temp = data;
        temp.ISBN = dataTransfer;
        console.log(temp);
        setData(temp);
    }

    function changeLocation(dataTransfer) {
        let temp = data;
        temp.location = dataTransfer;
        console.log(temp);
        setData(temp);
    }

    function changeAuthor(dataTransfer) {
        let temp = data;
        temp.author = dataTransfer;
        console.log(temp);
        setData(temp);
    }

    function changeType(dataTransfer) {
        let temp = data;
        temp.type = dataTransfer;
        console.log(temp);
        setData(temp);
    }

    function changePublisher(dataTransfer) {
        let temp = data;
        temp.publisher = dataTransfer;
        console.log(temp);
        setData(temp);
    }

    function changeReleaseDate(dataTransfer) {
        let temp = data;
        temp.releaseDate = dataTransfer;
        console.log(temp);
        setData(temp);
    }

    function changeImage(dataTransfer) {
        setImage(dataTransfer);
    }

    function create() {
        if (data.ISBN == "" || data.name == "" || data.author == -1 || data.publisher == -1 || data.releaseDate == "" || data.location == "" || image == null) {
            alert("Dữ liệu nhập vào không hợp lệ");
            return;
        }

        async function fetchAPI() {
            let urlUpload = config.severAPi.hostUrl + ":8081/book/upload_book_image";
            let body = new FormData();
            body.append("file", image);
            await fetch(urlUpload, {
                method: "post",
                headers: {"x-access-token": localStorage.quanlythuvien_accesstoken },
                body:  body
            })
                .then(res => res.json())
                .then(dataReceive => {
                    if(dataReceive.result){
                        let temp = data;
                        temp.image = dataReceive.data;
                        console.log("temp",temp);
                        setData(temp);
                    }
                    else{
                        return;
                    }
                });

                let urlInfo = config.severAPi.hostUrl + ":8081/book/add_new_book_info";
                await fetch(urlInfo, {
                    method: "post",
                    headers: { "Content-Type": "application/json", "x-access-token": localStorage.quanlythuvien_accesstoken },
                    body:  JSON.stringify(data)
                })
                    .then(res => res.json())
                    .then(data => {
                        if(data.result){
                            alert("Tạo đầu sách thành công!");
                        }
                        else{
                            alert("Đã có lỗi xảy ra. Vui lòng kiểm tra lại!")
                            return;
                        }
                    });
        }

        fetchAPI();

    }

    return (
        <Container>
            <InputConTainer label="Tên:" byData={changeName} />
            <InputConTainer label="ISBN:" byData={changeISBN} />
            <InputConTainer label="Vị trí:" byData={changeLocation} />
            <SelectedBoxContainer label="Tác giả:" data={authors} byData={changeAuthor} />
            <SelectedBoxContainer label="Thể loại:" data={typeBooks} byData={changeType} />
            <SelectedBoxContainer label="Nhà xuất bản:" data={publishers} byData={changePublisher} />
            <DatePickerContainer label="Ngày phát hành:" byData={changeReleaseDate} />
            <FileInputContainer label="Ảnh đại diện:" byData={changeImage} />
            <ButtonContainer bindEventCreate={create} />
        </Container>
    );
}

const mapStateToProps = state => {
    console.log("state in new Booktitle", state);
    return {
        authors: state.authors,
        type_books: state.type_books,
        publishers: state.publishers
    }
}

const mapDispatchToProps = (dispatch, props) => {
    return {
        
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(Form);