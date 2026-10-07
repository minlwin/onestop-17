'use client'

import FormsInput from "@/components/forms/forms-input";
import FormsSelect from "@/components/forms/forms-select";
import { Button } from "@/components/ui/button";
import { Card, CardContent } from "@/components/ui/card";
import ManagementTemplate from "@/components/widgets/pages/management-template";
import { UserListItem, UserSearch, userStatusList } from "@/lib/types/management/user.model";
import { executeAction } from "@/lib/utils";
import { Plus, Search } from "lucide-react";
import Link from "next/link";
import { useState } from "react";
import { useForm } from "react-hook-form";

export default function UserManagementPage() {

    const [list, setList] = useState<UserListItem[]>([])

    function search(form : UserSearch) {
        executeAction(async () => {

        })
    }

    return (
        <ManagementTemplate page="User Management" >
            <Card>
                <CardContent>
                    <SearchForm onSearch={search} />
                </CardContent>
            </Card>
        </ManagementTemplate>
    )
}

function SearchForm({onSearch} : {onSearch : (form:UserSearch) => void}) {
    const form = useForm<UserSearch>({defaultValues : {
        status : "",
        createdFrom: "",
        createdTo: "",
        keyword: ""
    }})

    return (
        <form onSubmit={form.handleSubmit(onSearch)} className="flex items-end gap-4">
            <FormsSelect control={form.control} name="status" label="Status" options={[
                {label : "Search All", value : ""},
                ...(userStatusList.map(item => ({
                    label : item,
                    value: item
                })))
            ]} className="w-fit" />

            <FormsInput control={form.control} name="createdFrom" label="Created From" type="date" className="w-fit" />
            <FormsInput control={form.control} name="createdTo" label="Created To" type="date" className="w-fit" />
            <FormsInput control={form.control} name="keyword" label="Keyword" className="w-fit" />

            <nav>
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
    )
}