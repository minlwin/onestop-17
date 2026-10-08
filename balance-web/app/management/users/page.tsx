'use client'

import FormsInput from "@/components/forms/forms-input";
import FormsSelect from "@/components/forms/forms-select";
import { Button } from "@/components/ui/button";
import { Card, CardContent } from "@/components/ui/card";
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table";
import NoSearchResult from "@/components/widgets/no-search-result";
import ManagementTemplate from "@/components/widgets/pages/management-template";
import { UserListItem, UserSearch, userStatusList } from "@/lib/types/management/user.model";
import { executeAction } from "@/lib/utils";
import { ChevronRight, Plus, Search } from "lucide-react";
import Link from "next/link";
import { useState } from "react";
import { useForm } from "react-hook-form";
import * as action from "@/lib/service/action/management/user-management.action"

export default function UserManagementPage() {

    const [list, setList] = useState<UserListItem[]>([])

    function search(form : UserSearch) {
        executeAction(async () => {
            const response = await action.search(form)
            setList(response)
        })
    }

    return (
        <ManagementTemplate page="User Management" >
            <SearchForm onSearch={search} />
            <ResultTable list={list} />
        </ManagementTemplate>
    )
}

function ResultTable({ list } : { list : UserListItem [] }) {

    if(list.length == 0) {
        return (
            <NoSearchResult data="User" />
        )
    }

    return (
        <Card>
            <CardContent>
                <Table>
                    <TableHeader>
                        <TableRow>
                            <TableHead>Name</TableHead>
                            <TableHead>Phone</TableHead>
                            <TableHead>Email</TableHead>
                            <TableHead>Status</TableHead>
                            <TableHead>Assign Date</TableHead>
                            <TableHead>Activated At</TableHead>
                            <TableHead>Retired At</TableHead>
                            <TableHead></TableHead>
                        </TableRow>
                    </TableHeader>

                    <TableBody>
                    {list.map(item => 
                        <TableRow key={item.id}>
                            <TableCell>{item.name}</TableCell>
                            <TableCell>{item.phone}</TableCell>
                            <TableCell>{item.email}</TableCell>
                            <TableCell>{item.status}</TableCell>
                            <TableCell>{item.assignDate}</TableCell>
                            <TableCell>{item.activatedAt}</TableCell>
                            <TableCell>{item.retiredAt}</TableCell>
                            <TableCell>
                                <Link href={`/management/users/${item.id}`}>
                                    <ChevronRight size={20} />
                                </Link>
                            </TableCell>
                        </TableRow>
                    )}    
                    </TableBody>
                </Table>
            </CardContent>
        </Card>
    )
}

function SearchForm({onSearch} : {onSearch : (form:UserSearch) => void}) {
    const form = useForm<UserSearch>({defaultValues : {
        status : "",
        assignFrom: "",
        assignTo: "",
        keyword: ""
    }})

    return (
        <Card>
            <CardContent>

                <form onSubmit={form.handleSubmit(onSearch)} className="flex items-end gap-4">
                    <FormsSelect control={form.control} name="status" label="Status" options={[
                        {label : "Search All", value : ""},
                        ...(userStatusList.map(item => ({
                            label : item,
                            value: item
                        })))
                    ]} className="w-fit" />

                    <FormsInput control={form.control} name="assignFrom" label="Created From" type="date" className="w-fit" />
                    <FormsInput control={form.control} name="assignTo" label="Created To" type="date" className="w-fit" />
                    <FormsInput control={form.control} name="keyword" label="Keyword" className="w-fit" />

                    <nav className="space-x-2">
                        <Button type="submit">
                            <Search /> Search
                        </Button>

                        <Button variant={'destructive'} nativeButton={false} render={
                            <Link href={'/management/users/create'} />
                        }>
                            <Plus /> Create
                        </Button>

                    </nav>

                </form>                
            </CardContent>
        </Card>
    )
}