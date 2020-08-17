import React from 'react';
import {Table, Button} from 'react-bootstrap';
import styles from '../static/styles.module.css'
import { useHistory } from "react-router-dom";
import { connect } from 'react-redux';
import * as actions from '../../../actions/index';
import config from '../../../asset/config.json'

function TableContainer(props) {
    const history = useHistory();

    const listItems = props.listBook.map((element, index) =>
        <tr key={index}>
            <td>{element.ID}</td>
            <td>{element.ISBN}</td>
            <td>{element.statusName}</td>
            <td>
                <Button variant="danger" className={[styles.myButtonDetail, "ml-2"].join(" ")} onClick = {() =>deleteBook(element)}><i className="fa fa-trash"></i></Button>
            </td>
        </tr>
    );

    function deleteBook(data){
        let url = config.severAPi.hostUrl + ":8081/book/delete_book/" + data.ID;
        fetch(url, {
            method: "get",
            headers: { "Content-Type": "application/json", "x-access-token": localStorage.quanlythuvien_accesstoken },
        })
            .then(res => res.json())
            .then(dataReceive => {
                if(dataReceive.result){
                    alert("Xóa sách thành công!")
                    props.onDeleteBook(data);
                }
                else{
                    alert("Đã có lỗi xảy ra, vui lòng thử lại");
                }
            });
    }

    return (
        <Table striped bordered hover>
            <thead>
                <tr>
                    <th width={'5%'}>ID</th>
                    <th width={'45%'}>ISBN</th>
                    <th width={'30%'}>Trạng thái</th>
                    <th width={'20%'}>
                    </th>
                </tr>
            </thead>
            <tbody>
            {listItems}
            </tbody>
        </Table>
    );
}

const mapStateToProps = state => {
    return {
        
    }
}

const mapDispatchToProps = (dispatch, props) => {
    return {
        onDeleteBook: (item) => {
            dispatch(actions.deleteBook(item));
        }
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(TableContainer);