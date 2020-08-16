import * as types from '../constants/ActionType';
var initialState = [];

var myReducer = (state = initialState, action) => {
    switch (action.type) {
        case types.SET_LIST_PUBLISHER:
            state = action.list;
            return [...state];
        case types.ADD_PUBLISHER:
            return [...state, action.pulisher];
        case types.EDIT_PUBLISHER:
            let foundIndex = state.findIndex(x => x.id == action.author.id);
            let temp = [...state];
            temp[foundIndex] = action.author;
            return [...temp];
        default:
            return state;

    }
    return state;
}

export default myReducer;