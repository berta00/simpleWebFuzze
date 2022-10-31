package main;

import (
    "fmt"
    "net/http"
)

func main(){
    http.HandleFunc("/", mainRoute)
    http.HandleFunc("/DATABASE", dbRoute)

    http.ListenAndServe(":8080", nil)
}

func mainRoute(w http.ResponseWriter, r *http.Request){
    fmt.Fprintln(w, "welcome, home")
    fmt.Println("new conn: /")
}

func dbRoute(w http.ResponseWriter, r *http.Request){
    fmt.Fprintln(w, "welcome, database")
    fmt.Println("new conn: /DATABASE")
}