'use client'

import FormsInput from "@/components/forms/forms-input";
import { Button } from "@/components/ui/button";
import ManagementTemplate from "@/components/widgets/pages/management-template";
import { UserForm, userSchema } from "@/lib/types/management/user.model";
import { executeAction } from "@/lib/utils";
import { zodResolver } from "@hookform/resolvers/zod";
import { Save } from "lucide-react";
import { useForm } from "react-hook-form";

import * as action from "@/lib/service/action/management/user-management.action"

export default function CreateUserPage() {

    const form = useForm({
        resolver: zodResolver(userSchema),
        defaultValues: {
            name : "",
            phone: "",
            email : "",
            assignDate: ""
        }
    })

    function save(form : UserForm) {
        executeAction(async () => {
            await action.create(form)
        })
    }

    return (
        <ManagementTemplate page="Create User" links={[
            {name : "User Management", route: "/management/users"}
        ]}>
            <section className="w-1/2">
                <form onSubmit={form.handleSubmit(save)} className="space-y-4">
                    <FormsInput control={form.control} name="name" label="User Name" />
                    <FormsInput control={form.control} name="assignDate" label="Assign Date" type="date" />
                    <FormsInput control={form.control} name="phone" label="Phone" />
                    <FormsInput control={form.control} name="email" label="Email" />

                    <Button type="submit">
                        <Save /> Create User
                    </Button>
                </form>
            </section>
        </ManagementTemplate>
    )
}