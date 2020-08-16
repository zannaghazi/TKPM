import React, { useState, useEffect } from 'react';
import { Container, Row, Col } from 'react-bootstrap';
import InputConTainer from './InputContainer'
import DatePickerContainer from './DatePickerContainer'
import SelectedBoxContainer from './SelectedBoxContainer'
import ButtonContainer from './ButtonContainer';
import { useHistory } from "react-router-dom";
import { useParams } from 'react-router-dom';
import config from '../../../asset/config.json'
import FileInputContainer from './FileInputContainer'
import { connect } from 'react-redux';

function Form(props) {
    const history = useHistory();
    const params = useParams();

    console.log("param", params);
    const [authors, setAuthors] = useState([]);
    const [typeBooks, setTypeBooks] = useState([]);
    const [publishers, setPublishers] = useState([]);
    const [data, setData] = useState({});
    const [image, setImage] = useState(null);

    useEffect(() => {
        let url = config.severAPi.hostUrl + ":8081/book/get_book_by_isbn/" + params.isbn;
        fetch(url, {
            method: "get",
            headers: { "Content-Type": "application/json", "x-access-token": localStorage.quanlythuvien_accesstoken },
        })
            .then(res => res.json())
            .then(dataReceive => {
                console.log("111111111111111", dataReceive)
                setData(dataReceive);
            });
    }, []);

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

    function edit() {
        if (data.ISBN == "" || data.name == "" || data.author == -1 || data.publisher == -1 || data.releaseDate == "" || data.location == "") {
            alert("Dữ liệu nhập vào không hợp lệ");
            return;
        }

        let url = config.severAPi.hostUrl + ":8081/book/update_book_info";
        fetch(url, {
            method: "post",
            headers: { "Content-Type": "application/json", "x-access-token": localStorage.quanlythuvien_accesstoken },
            body: JSON.stringify(data)
        })
            .then(res => res.json())
            .then(data => {
                if (data.result) {
                    alert("Cập nhật đầu sách thành công!");
                    history.push("/dashboard/booktitle_management");
                }
                else {
                    alert("Đã có lỗi xảy ra. Vui lòng kiểm tra lại!")
                    return;
                }
            });
    }

    return (
        <Container>
            <InputConTainer label="Tên:" byData={changeName} value={data.name} />
            <InputConTainer label="ISBN:" byData={changeISBN} value={data.ISBN} is_disabled={true} />
            <InputConTainer label="Vị trí:" byData={changeLocation} value={data.location} />
            <SelectedBoxContainer label="Tác giả:" data={authors} byData={changeAuthor} value={data.authorID} />
            <SelectedBoxContainer label="Thể loại:" data={typeBooks} byData={changeType} value={data.type} />
            <SelectedBoxContainer label="Nhà xuất bản:" data={publishers} byData={changePublisher} value={data.publisherID} />
            <DatePickerContainer label="Ngày phát hành:" byData={changeReleaseDate} value={data.releaseDate} />
            <FileInputContainer label="Ảnh đại diện:" byData={changeImage} />
            <ButtonContainer bindEventEdit={edit} />
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