import { Button } from "@/components/ui/button";
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/card";
import { Item, ItemContent, ItemDescription, ItemTitle } from "@/components/ui/item";
import AuditSection from "@/components/widgets/audit-section";
import Info from "@/components/widgets/info";
import ManagementTemplate from "@/components/widgets/pages/management-template";
import SectionTitle from "@/components/widgets/section-title";

import * as client from "@/lib/service/client/management/subscription-plan.client"
import { Check, Pencil, Shield } from "lucide-react";
import Link from "next/link";

export default async function SubscriptionPlanDetailsPage(props : PageProps<'/management/master/plans/[id]'>) {

    const { id } = await props.params
    const response = await client.findById(id)

    return (
        <ManagementTemplate page="Details" links={[
            {name : "Subscription Plans", route : "/management/master/plans"}
        ]}>
            <Card>
                <CardHeader>
                    <CardTitle className="flex justify-between">
                        <div className="flex gap-2 items-center">
                            <Shield /> 
                            <span className="text-2xl">{response.name} Plan</span>
                        </div>
                        <Button nativeButton={false} render={
                            <Link href={`/management/master/plans/${id}/edit`} />
                        }>
                            <Pencil /> Edit Plan
                        </Button>
                    </CardTitle>
                    <CardDescription>{response.description}</CardDescription>
                </CardHeader>

                <CardContent className="space-y-6">

                    <section className="space-y-4">
                        <SectionTitle title="Features" />
                        <div className="space-y-2">
                            {response.features.map((item, index) => 
                                <div key={index} className="flex gap-2">
                                    <Check size={20} />
                                    <span>{item.name}</span>
                                </div>
                            )}
                        </div>
                    </section>

                    <section className="space-y-4">
                        <SectionTitle title="Fee and Limits" />

                        <section className="grid grid-cols-4 gap-4">
                            <Info label="Monthly Fee" value={`${response.price} MMK`} /> 
                            <Info label="Employee Limit" value={response.maxEmployee} /> 
                            <Info label="Ledger Limit" value={response.maxLedger} /> 
                            <Info label="Daily Entry Limit" value={response.maxEntry} /> 
                        </section>
                    </section>

                    <AuditSection createdBy={response.createdBy} createdAt={response.createdAt}
                        modifiedBy={response.modifiedBy} modifiedAt={response.modifiedAt} />
                        
                </CardContent>
            </Card>
        </ManagementTemplate>
    )
}
