import React from 'react';
import { Table, Button } from 'react-bootstrap';
import { useHistory } from "react-router-dom";
import { connect } from 'react-redux';
import * as actions from '../../../actions/index';
import styles from '../static/styles.module.css'
import config from '../../../asset/config.json'

function TableContainer(props) {
    const history = useHistory();

    function editItem(event, data) {
        console.log("mydata", data);
        if (!data) {
            return;
        }
        history.push("/dashboard/edit_author/" + data.id);
    }

    function deleteAuthor(data){
        let url = config.severAPi.hostUrl + ":8081/book/delete_author/" + data.id;
        fetch(url, {
            method: "get",
            headers: { "Content-Type": "application/json", "x-access-token": localStorage.quanlythuvien_accesstoken },
        })
            .then(res => res.json())
            .then(dataReceive => {
                if(dataReceive.result){
                    alert("Xóa tác giả thành công!")
                    props.onDeleteAuthor(data);
                }
                else{
                    alert("Đã có lỗi xảy ra, vui lòng thử lại");
                }
            });
    }

    const listItems = props.listAuthor.map((element, index) => {
        if (element.id > 0) {
            return (<tr key={index}>
                <td>{element.id}</td>
                <td>{element.name}</td>
                <td>{element.updatedDate}</td>
                <td>{element.updatedAccount}</td>
                <td>
                    <Button variant="primary" className={styles.myButtonDetail} onClick={(event) => editItem(event, element)}><i className="fa fa-edit"></i></Button>
                    <Button variant="danger" className={[styles.myButtonDetail, "ml-2"].join(" ")} onClick={() => deleteAuthor(element)}><i className="fa fa-trash"></i></Button>
                </td>
            </tr>)
        }
    }
    );

    return (
        <Table striped bordered hover>
            <thead>
                <tr>
                    <th width={'2%'}>#</th>
                    <th width={'38%'}>Tên</th>
                    <th width={'22%'}>Ngày cập nhật</th>
                    <th width={'28%'}>Người cập nhật</th>
                    <th width={'10%'}>
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
        onDeleteAuthor: (item) => {
            dispatch(actions.deleteAuthor(item));
        }
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(TableContainer);