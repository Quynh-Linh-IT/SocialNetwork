import { URL_API } from "~/config";
const { createSlice, createAsyncThunk } = require("@reduxjs/toolkit");
const  initialState = {
    user:{
        id: '',
        firstName: '',
        lastName: '',
        email: '',
        address: '',
        dateOfBirth: '',
        image: ''
    },
    token : '',
    isAuthenticated: false,
    isLoading: false,
    msg:'',
}

// HANDLE REGISTER USER
const signUpUser = createAsyncThunk('signUpUser',async(body)=> {
    try {
        const res = await fetch(URL_API + 'api/v1/users/add', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(body),
        });
        const data = await res.json();
        return data;
    } catch (error) {
        console.error('Error fetching data:', error);
        throw error;
    }
});

// HANDLE SIGN IN USER
const signInUser = createAsyncThunk('signInUser',async(body)=> {
    try {
        const res = await fetch(URL_API + 'api/v1/users/login', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(body),
        });
        const data = await res.json();
        return data;
    } catch (error) {
        console.error('Error fetching data:', error);
        throw error;
    }
});

// HANDLE GET INFO USER BY TOKEN
const getInfoUserByToken = createAsyncThunk('getInfoUserByToken',async(slug)=> {
    try {
        const res = await fetch(URL_API + 'api/v1/users/token/'+slug, {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        });
        const data = await res.json();
        return data;
    } catch (error) {
        console.error('Error fetching data:', error);
        throw error;
    }
});

// GET LIST USER BY FRIENDS STATUS
const getListSuggestedFriends = createAsyncThunk('getListSuggestedFriends',async(body)=> {
    try {
        const res = await fetch(URL_API + 'api/v1/users/listSuggested', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body : JSON.stringify(body)
        });
        const data = await res.json();
        return data;
    } catch (error) {
        console.error('Error fetching data:', error);
    }
});

// GET LIST USER FRIENDS
const getListUserFriends = createAsyncThunk('getListUserFriends',async(body)=> {
    try {
        const res = await fetch(URL_API + 'api/v1/users/listFriends', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body : JSON.stringify(body)
        });
        const data = await res.json();
        return data;
    } catch (error) {
        console.error('Error fetching data:', error);
    }
});

// HANDLE GET USER BY REQUEST SENT
const getListUserRequestSent = createAsyncThunk('getListUserRequestSent',async(body)=> {
    try {
        const res = await fetch(URL_API + 'api/v1/users/requestFriends', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(body)
        });
        const data = await res.json();
        return data;
    } catch (error) {
        console.error('Error fetching data:', error);
        throw error;
    }
});

// HANDLE GET USER BY REQUEST SENT
const getListUserBySearch = createAsyncThunk('getListUserBySearch',async(body)=> {
    try {
        const {name} = body;
        const res = await fetch(URL_API + `api/v1/users/searchByName?name=${name}`, {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        });
        const data = await res.json();
        return data;
    } catch (error) {
        console.error('Error fetching data:', error);
        throw error;
    }
});

// HANDLE GET USER BY VERIFY REQUEST
const getListUserVerifyRequest = createAsyncThunk('getListUserVerifyRequest',async(body)=> {
    try {
        const res = await fetch(URL_API + 'api/v1/users/verifyRequest', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(body)
        });
        const data = await res.json();
        return data;
    } catch (error) {
        console.error('Error fetching data:', error);
        throw error;
    }
});

// HANDLE GET DETAIL USER BY ID
const getDetailUserById = createAsyncThunk('getListUserVerifyRequest',async(body)=> {
    try {
        const {id} = body;
        const res = await fetch(URL_API + `api/v1/users/getDetailUser/${id}`, {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        });
        const data = await res.json();
        return data;
    } catch (error) {
        console.error('Error fetching data:', error);
        throw error;
    }
});

// HANDLE UPDATE USER BY IMAGE IN DB
const updateImageUserDB = createAsyncThunk('updateImageUserDB',async(body)=> {
    try {
        const res = await fetch(URL_API + 'api/v1/users/updateImage', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(body)
        });
        const data = await res.json();
        return data;
    } catch (error) {
        console.error('Error fetching data:', error);
        throw error;
    }
});

// HANDLE UPDATE USER IN DB
const updateUserDB = createAsyncThunk('updateUserDB',async(body)=> {
    try {
        const res = await fetch(URL_API + 'api/v1/users/update', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(body)
        });
        const data = await res.json();
        return data;
    } catch (error) {
        console.error('Error fetching data:', error);
        throw error;
    }
});

// HANDLE UPDATE USER IN DB
const updateStatusByFriends = createAsyncThunk('updateStatusByFriends',async(body)=> {
    try {
        const res = await fetch(URL_API + 'api/v1/users/updateStatus', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(body)
        });
        const data = await res.json();
        return data;
    } catch (error) {
        console.error('Error fetching data:', error);
        throw error;
    }
});

const authSlice = createSlice({
    name: "auth",
    initialState,
    reducers: {
        updateUSer : (state,action) => {
            const {firstName,lastName,email,address,dateOfBirth,image} = action.payload;
            state.user.firstName = firstName;
            state.user.lastName = lastName;
            state.user.email = email;
            state.user.address = address;
            state.user.dateOfBirth = dateOfBirth;
            state.user.image = image;
        }
    },
    extraReducers : (builder) => {
        // ================= SIGN UP =================
        builder.addCase(signUpUser.pending,(state,action) => {
            state.isLoading = true;
        });
        builder.addCase(signUpUser.fulfilled,(state,action) => {
            const {message} = action.payload;
            state.isLoading = false;
            state.msg = message;
        });
        builder.addCase(signUpUser.rejected,(state,action) => {
            state.isLoading = true;
        });
        // ================= SIGN IN =================
        builder.addCase(signInUser.pending,(state,action) => {
            state.isLoading = true;
        });
        builder.addCase(signInUser.fulfilled,(state,action) => {
            const { token , message , userID} = action.payload;
            state.isLoading = false;
            state.token = token;
            state.msg = message;
            state.user.userId = userID;
            // SAVE TOKEN USER IN LOCALSTRORAGE
            localStorage.setItem('token', token);
        });
        builder.addCase(signInUser.rejected,(state,action) => {
            state.isLoading = true;
        });
        // ================= GET INFO USER BY TOKEN =================
        builder.addCase(getInfoUserByToken.pending,(state,action) => {
            state.isLoading = true;
        });
        builder.addCase(getInfoUserByToken.fulfilled,(state,action) => {
            state.isLoading = false;
            const {id,firstName,lastName,email,address,dateOfBirth,image} = action.payload;
            // 
            state.user.id = id;
            state.user.firstName = firstName;
            state.user.lastName = lastName;
            state.user.email = email;
            state.user.address = address;
            state.user.dateOfBirth = dateOfBirth;
            state.user.image = image;
            state.msg = '';
        });
        builder.addCase(getInfoUserByToken.rejected,(state,action) => {
            state.isLoading = true;
        });
        // ================= UPDATE USER =================
        builder.addCase(updateUserDB.pending,(state,action) => {
            state.isLoading = true;
        });
        builder.addCase(updateUserDB.fulfilled,(state,action) => {
            state.isLoading = false;
            const {message} = action.payload;
            state.msg = message;
        });
        builder.addCase(updateUserDB.rejected,(state,action) => {
            state.isLoading = true;
        });
        // ================= SEARCH USER =================
        builder.addCase(getListUserBySearch.pending,(state,action) => {
            state.isLoading = true;
        });
        builder.addCase(getListUserBySearch.fulfilled,(state,action) => {
            state.isLoading = false;
        });
        builder.addCase(getListUserBySearch.rejected,(state,action) => {
            state.isLoading = true;
        });
    }
});
 
export default authSlice.reducer;
export {
    // GET ALL USERS LIST
    getListSuggestedFriends,
    // REGISTER USER
    signUpUser,
    // LOGIN
    signInUser,
    // GET USER INFORMATION
    getInfoUserByToken,
    // UPDATE USER INFORMATION
    updateUserDB,
    // UPDATE IMAGE USER INFORMATION
    updateImageUserDB,
    // UPDATE STATUS BY FRIENDS
    updateStatusByFriends,
    // GET USER TO REQUEST VERIFY
    getListUserVerifyRequest,
    // GET LIST USER TO REQUEST SENT
    getListUserRequestSent,
    // GET LIST USER FRIENDS
    getListUserFriends,
    // GET LIST USER BY SEARCH
    getListUserBySearch,
    // GET DETAIL USER BY ID
    getDetailUserById
};
export const {updateUSer} = authSlice.actions; 