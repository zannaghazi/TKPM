import React, { useState, useEffect } from 'react';
import config from '../../../asset/config.json'
import styles from '../static/styles.module.css'

function MyTablePagination(props) {
	const [items, setItems] = useState([]);
	const [isDisablePrev, setIsDisablePrev] = useState(false);
	const [isDisableNext, setIsDisableNext] = useState(false);

	useEffect(() => {
		console.log("list", props.listAuthor);
		let amount = 0;
		if (props.listPublisher.length % config.pagination.limit == 0) {
			amount = props.listPublisher.length / config.pagination.limit;
		}
		else {
			amount = props.listPublisher.length / config.pagination.limit + 1;
		}
		console.log("ammount", amount, props.listPublisher.length);
		let temp = [];
		if (props.active == 1) {
			setIsDisablePrev(true);
		}
		else {
			setIsDisablePrev(false);
		}
		console.log(amount, props.active);
		if (props.active <= amount && props.active + 1 > amount) {
			setIsDisableNext(true)
		}
		else {
			setIsDisableNext(false);
		}
		for (let number = 1; number <= amount; number++) {
			temp.push(
				<li className={[styles.myPaginationItem, props.active === number ? styles.myActive : styles.normalPagination, "page-item"].join(" ")} key={number}><a className="page-link" href="#" onClick={(event) => handlePaging(event, number)}>{number}</a></li>,
			);
		}
		setItems(temp);
		console.log(temp);
	}, [props.listAuthor, props.active]);

	function handlePaging(event, number) {
		event.preventDefault()
		props.setActive(number);
	}

	function handlePrev(event) {
		event.preventDefault()
		let number = props.active - 1;
		props.setActive(number);
	}

	function handleNext(event) {
		event.preventDefault()
		let number = props.active + 1;
		props.setActive(number);
	}

	return (
		<nav aria-label="Page navigation example">
			<ul className="pagination">
				<li className={[styles.myPaginationItem, "page-item"].join(" ")}><a className={["page-link", isDisablePrev ? styles.myDisabled : ""].join(" ")} href="#" onClick={handlePrev}>Trước</a></li>
				{items}
				<li className={[styles.myPaginationItem, "page-item"].join(" ")}><a className={["page-link", isDisableNext ? styles.myDisabled : ""].join(" ")} href="#" onClick={handleNext}>Sau</a></li>
			</ul>
		</nav>
	);
}

export default MyTablePagination;