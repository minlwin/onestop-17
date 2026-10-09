'use client'

import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { PaymentInfo, PaymentInfoForm, paymentInfoSchema } from "@/lib/types";
import { executeAction } from "@/lib/utils";
import { zodResolver } from "@hookform/resolvers/zod";
import { Pencil, Plus, Save } from "lucide-react";
import { useForm } from "react-hook-form";
import * as action from "@/lib/service/action/management/payment-info.action"
import FormsInput from "@/components/forms/forms-input";
import { Button } from "@/components/ui/button";

export default function PaymentInfoEditComponent({info} : {info? : PaymentInfo}) {

    const form = useForm({
        resolver: zodResolver(paymentInfoSchema),
        defaultValues: {
            provider: info?.provider || "",
            accountNo: info?.accountNo || "",
            accountName: info?.accountName || ""
        }
    })

    function save(form : PaymentInfoForm) {
        executeAction(async () => {
            if(info) {
                action.update(info.id, form)
            } else {
                action.create(form)
            }
        })
    }

    return (
        <Card>
            <CardHeader>
                <CardTitle className="flex gap-2 items-center">
                    {info ? <Pencil size={20} /> : <Plus size={20} />}
                    <span>{info ? "Edit" : "Create"} Payment Infomation</span>
                </CardTitle>
            </CardHeader>

            <CardContent className="w-1/2">
                <form onSubmit={form.handleSubmit(save)} className="space-y-4">
                    <FormsInput control={form.control} name="provider" label="Payment Provider" />
                    <FormsInput control={form.control} name="accountNo" label="Account No" />
                    <FormsInput control={form.control} name="accountName" label="Account Name" />

                    <Button type="submit">
                        <Save /> Save Payment Information
                    </Button>
                </form> 
            </CardContent>
        </Card>
    )
}